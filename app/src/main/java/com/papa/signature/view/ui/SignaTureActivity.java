package com.papa.signature.view.ui;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.papa.signature.R;
import com.papa.signature.model.MessageBody;
import com.papa.signature.model.PictureRes;
import com.papa.signature.model.ProtocolRes;
import com.papa.signature.model.remote.BaseObserver;
import com.papa.signature.utils.CreateBitmatUtils;
import com.papa.signature.utils.ExampleUtil;
import com.papa.signature.utils.RetrofitUtil002;
import com.papa.signature.utils.RetrofitUtil003;
import com.papa.signature.views.SignatureView;

import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class SignaTureActivity extends AppCompatActivity {

    private ScrollView scrollView;
    private String path01 = "";
    private String path02 = "";
    private String message;
    private MessageBody messageBody;
    private TextView proText;
    private TextView cardNo;
    private TextView crateCardName;
    private TextView dengji;
    private TextView youxiaoqi;
    private TextView beizhu;
    private ImageView success_logo;
    private File photo;
    private String cookie;
    private String signature;
    private String images;
    private Bitmap nBitmap;
    private Dialog dialog;
    private TextView textView;
    private ProgressBar progress;
    private View inflate;
    private PopupWindow popupWindow;
    private LinearLayout parentView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singature);
        message = getIntent().getStringExtra("message");
        initView();
        jsonToObject(message);
        getProtocol();
    }

    /**
     * 获取协议信息
     */
    private void getProtocol() {
        Map<String, Object> map = new HashMap<>();
        map.put("business_id", messageBody.getBusiness_id());
        map.put("trade_type_id", messageBody.getTrade_type_id());
        RetrofitUtil002.getInstance().getAutoAPIService().getProtoolConf("Authorization=" + messageBody.getAuthorization(), map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<ProtocolRes>(SignaTureActivity.this) {
                    @Override
                    protected void onSuccees(ProtocolRes res) throws Exception {
                        if (res.getCode() == 200) {
                            proText.setText(Html.fromHtml(res.getData().getProtocol()));
                        } else {
                            proText.setText(res.getMessage());
                        }
                    }

                    @Override
                    protected void onFailure(JSONObject jsonObject, boolean isNetWorkError) throws Exception {


                    }
                });
    }

    /**
     * 把字符串解析成对象
     *
     * @param message
     */
    private void jsonToObject(String message) {
        Gson gson = new Gson();
        if (message.length() > 0) {
            messageBody = gson.fromJson(message, MessageBody.class);
            cookie = "token=" + messageBody.getAuthorization() + ";stadium_id=1";
            cardNo = findViewById(R.id.cardNo);
            cardNo.setText("卡号:" + messageBody.getCard_num());
            crateCardName = findViewById(R.id.crateCardName);
            crateCardName.setText("办卡人:" + messageBody.getName() + " " + messageBody.getPhone());
            dengji = findViewById(R.id.dengji);
            dengji.setText(messageBody.getCard_leave());
            youxiaoqi = findViewById(R.id.youxiaoqi);
            youxiaoqi.setText("有效期:" + messageBody.getDate());
            beizhu = findViewById(R.id.beizhu);
            beizhu.setText("备注:" + messageBody.getDes());
        }
    }

    /**
     *
     */
    private void initView() {
        parentView = findViewById(R.id.parent);
        scrollView = findViewById(R.id.scrollview);
        proText = findViewById(R.id.content);
        //签名成功后显示该图片
        success_logo = findViewById(R.id.success_logo);
        final Button mClearButton = (Button) findViewById(R.id.clear_button);
        final Button mSaveButton = (Button) findViewById(R.id.save_button);
        final SignatureView mSignaturePad = (SignatureView) findViewById(R.id.signature_pad);
        final TextView showMessage = findViewById(R.id.showMessage);

        mSignaturePad.setOnSignedListener(new SignatureView.OnSignedListener() {
            @Override
            public void onSigned() {
                mClearButton.setEnabled(true);
                showMessage.setVisibility(View.GONE);
            }

            @Override
            public void onClear() {
                mClearButton.setEnabled(false);
                showMessage.setVisibility(View.VISIBLE);
            }
        });
        mClearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSignaturePad.clear();
            }
        });
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //是否为空
                boolean isHaveSigna = mSignaturePad.isEmpty();
                if (isHaveSigna) {
                    Toast.makeText(SignaTureActivity.this, "请签名！", Toast.LENGTH_SHORT).show();
                    return;
                }
                //截屏图片
                Bitmap bitmapByView01 = CreateBitmatUtils.scrollViewScreenShot(scrollView);
                //签字图片
                Bitmap signatureBitmap = mSignaturePad.getSignatureBitmap();
                //压缩图片
                Bitmap bitmap = compressScale(bitmapByView01);
                nBitmap = compressScale(signatureBitmap);

                if (addSignatureToGallery(bitmap, "view")) {
                    //把图片上传到服务器
                    doUploadImg(1);

                } else {
                    Toast.makeText(SignaTureActivity.this, "保存失败，请重新签名", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 图片保存成功，上传到服务器
     */
    private void doUploadImg(int sta) {
        //把图片上传到服务器
        List<File> files = new ArrayList<>();
        files.add(photo);
        HashMap<String, String> map = new HashMap<>();
        map.put("picname", "protocolImg");
        if (sta == 1) {
            createPopupWindow();
        }
        uploadFilse(files, map, sta);

    }
    //=================正式开发需要封装起来====================

    /**
     *  
     *  * 图片按比例大小压缩方法 
     *  * 
     *  * @param image （根据Bitmap图片压缩） 
     *  * @return 
     *  
     */
    public static Bitmap compressScale(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        if (baos.toByteArray().length / 1024 > 1024) {//判断如果图片大于1M,进行压缩避免在生成图片（BitmapFactory.decodeStream）时溢出
            baos.reset();//重置baos即清空baos
            image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//这里压缩50%，把压缩后的数据存放到baos中
        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        float hh = 1920f;//这里设置高度为800f
        float ww = 1080f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
                    be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置缩放比例
        //newOpts.inPreferredConfig = Bitmap.Config.RGB_565;//降低图片从ARGB888到RGB565
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        isBm = new ByteArrayInputStream(baos.toByteArray());
        bitmap = BitmapFactory.decodeStream(isBm, null, newOpts);
        // return compressImage(bitmap);//压缩好比例大小后再进行质量压缩
        return bitmap;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 4:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "已打开权限！", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "请打开权限！", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        /**
         * 设置为横屏
         */
//        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
//            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//        }
        super.onResume();
    }


    public static void savePhotoToSDCard(Bitmap photoBitmap, String path, String photoName) {
        if (checkSDCardAvailable()) {
            File dir = new File(path);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            File photoFile = new File(path, photoName + ".png");
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(photoFile);
                if (photoBitmap != null) {
                    if (photoBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream)) {
                        fileOutputStream.flush();
                    }
                }
            } catch (FileNotFoundException e) {
                photoFile.delete();
                e.printStackTrace();
            } catch (IOException e) {
                photoFile.delete();
                e.printStackTrace();
            } finally {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean checkSDCardAvailable() {
        return android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }

    public File getAlbumStorageDir(String albumName) {
        File file = new File(Environment.getExternalStorageDirectory(), albumName);
        if (!file.mkdirs()) {
            Log.e("SignaturePad", "Directory not created");
        }
        return file;
    }

    public void saveBitmapToJPG(Bitmap bitmap, File photo) throws IOException {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        OutputStream stream = new FileOutputStream(photo);
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        stream.close();
    }

    public boolean addSignatureToGallery(Bitmap signature, String photoName) {
        boolean result = false;
        try {
            photo = new File(getAlbumStorageDir("papa"), String.format(photoName + ".png", System.currentTimeMillis()));
            Log.i("URL", getAlbumStorageDir("papa") + String.format(photoName + ".png", System.currentTimeMillis()));
            saveBitmapToJPG(signature, photo);
            Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            Uri contentUri = Uri.fromFile(photo);
            mediaScanIntent.setData(contentUri);
            SignaTureActivity.this.sendBroadcast(mediaScanIntent);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }


    public void uploadFilse(List<File> files, HashMap<String, String> mapStr, final int sta) {
        ArrayList<MultipartBody.Part> parts = new ArrayList<>();
        for (int i = 0; i < files.size(); i++) {
            File file = files.get(i);
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            //服务器上的文件参数  name   filename
            MultipartBody.Part jokeFile = MultipartBody.Part.createFormData("image_path", "member_agreement", requestFile);
            parts.add(jokeFile);
        }
        RetrofitUtil003.getInstance().getAutoAPIService().uploadFile(cookie, mapStr, parts)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new BaseObserver<PictureRes>(SignaTureActivity.this) {
                    @Override
                    protected void onRequestStart() {
                        super.onRequestStart();
                        //开始访问服务器tu'p
                    }

                    @Override
                    protected void onRequestEnd() {
                        super.onRequestEnd();
                        //服务器访问结束
                    }

                    @Override
                    protected void onSuccees(PictureRes responseBody) throws Exception {
                        //整个签名的图片
                        if (sta == 1) {
                            images = responseBody.getData().getImgPath();
                            if (addSignatureToGallery(nBitmap, "sigin")) {
                                doUploadImg(2);
                            }
                        } else if (sta == 2) {
                            //签名的图片
                            signature = responseBody.getData().getImgPath();
                            update();
                        }
                    }

                    @Override
                    protected void onFailure(JSONObject jsonObject, boolean isNetWorkError) throws Exception {
                        Toast.makeText(SignaTureActivity.this, jsonObject.getString("error") + "，请重新保存图片！", Toast.LENGTH_SHORT).show();
                        if (popupWindow != null && popupWindow.isShowing()) {
                            popupWindow.dismiss();
                        }
                    }
                });
    }


    public void update() {
        Map<String, Object> map = new HashMap<>();
        map.put("agreement_id", messageBody.getAgreement_id());
//        map.put("token", messageBody.getToken());
        map.put("device_id", ExampleUtil.getDeviceId(this));
        map.put("images", images);
        map.put("signature", signature);
        RetrofitUtil002.getInstance().getAutoAPIService().update(map).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new BaseObserver<ResponseBody>(SignaTureActivity.this) {
                    @Override
                    protected void onSuccees(ResponseBody responseBody) throws Exception {
                        String string = responseBody.string();
                        JSONObject jsonObject = new JSONObject(string);
                        boolean is_error = jsonObject.getBoolean("is_error");
                        initCud();
                    }

                    @Override
                    protected void onFailure(JSONObject jsonObject, boolean isNetWorkError) throws Exception {
                        Toast.makeText(SignaTureActivity.this, jsonObject.getString("error") + "，请重新保存图片！", Toast.LENGTH_SHORT).show();
                        if (popupWindow != null && popupWindow.isShowing()) {
                            popupWindow.dismiss();
                        }
                    }
                });
    }

    /**
     * 倒计时
     */
    private void initCud() {
        /**
         * CountDownTimer timer = new CountDownTimer(3000, 1000)中，
         * 第一个参数表示总时间，第二个参数表示间隔时间。
         * 意思就是每隔一秒会回调一次方法onTick，然后1秒之后会回调onFinish方法。
         */
        progress.setVisibility(View.GONE);
        CountDownTimer timer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                Message message = new Message();
                message.what = 0;
                message.obj = "协议上传成功" + millisUntilFinished / 1000 + "s后返回首页";
                handler.sendMessage(message);
            }

            @Override
            public void onFinish() {
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                    popupWindow = null;
                }
                SignaTureActivity.this.finish();
            }
        };
        //调用 CountDownTimer 对象的 start() 方法开始倒计时，也不涉及到线程处理
        timer.start();
    }


    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    String attentionStr = (String) msg.obj;
                    textView.setTextColor(Color.parseColor("#000000"));
                    textView.setText(attentionStr);
                    break;
            }
        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        if (popupWindow != null && popupWindow.isShowing()) {
            popupWindow.dismiss();
            popupWindow = null;
        }
    }

    /**
     * popwindow的创建
     */
    public void createPopupWindow() {
        inflate = LayoutInflater.from(this).inflate(R.layout.dialog_loading, null);
        progress = inflate.findViewById(R.id.progress);
        textView = inflate.findViewById(R.id.title);
        textView.setText("协议上传中···");
        popupWindow = new PopupWindow(inflate, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, false);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.showAtLocation(parentView, Gravity.CENTER, 0, 0);
    }
}
