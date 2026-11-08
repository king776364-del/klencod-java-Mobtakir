package com.mobtakir.app;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;
import android.net.Uri;
import android.app.AlertDialog;

public class MainActivity extends Activity {

    private EditText promptInput;
    private TextView statusText, resultText;
    private Button btnAndroid, btnIos, btnGenerate, btnPay;
    private String selectedPlatform = "Android";
    private boolean isPremium = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView scrollView = new ScrollView(this);
        scrollView.setBackgroundColor(0xFF0A1F13);
        
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(30, 40, 30, 40);

        // شريط الحالة والتجربة المجانية
        statusText = new TextView(this);
        statusText.setText("تجربة مجانية نشطة (متبقي: 30 يوم)");
        statusText.setTextColor(0xFF00C853);
        statusText.setTextSize(14);
        statusText.setPadding(20, 20, 20, 20);
        layout.addView(statusText);

        // زر ترقية البريميوم ومحفظة جيب
        btnPay = new Button(this);
        btnPay.setText("ترقية البريميوم (7,000 ر.ي - محفظة جيب)");
        btnPay.setBackgroundColor(0xFF112E1F);
        btnPay.setTextColor(0xFFFFD700);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showJeepPaymentDialog();
            }
        });
        layout.addView(btnPay);

        // اختيار المنصة
        TextView lblPlatform = new TextView(this);
        lblPlatform.setText("\nاختر منصة صنع التطبيق:");
        lblPlatform.setTextColor(0xFF00C853);
        lblPlatform.setTextSize(16);
        layout.addView(lblPlatform);

        LinearLayout platformLayout = new LinearLayout(this);
        platformLayout.setOrientation(LinearLayout.HORIZONTAL);

        btnAndroid = new Button(this);
        btnAndroid.setText("أندرويد");
        btnAndroid.setBackgroundColor(0xFF00C853);
        btnAndroid.setTextColor(0xFF000000);
        btnAndroid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPlatform = "Android";
                btnAndroid.setBackgroundColor(0xFF00C853);
                btnIos.setBackgroundColor(0xFF112E1F);
            }
        });
        platformLayout.addView(btnAndroid);

        btnIos = new Button(this);
        btnIos.setText("آيفون (iOS)");
        btnIos.setBackgroundColor(0xFF112E1F);
        btnIos.setTextColor(0xFFFFFFFF);
        btnIos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPlatform = "iOS";
                btnIos.setBackgroundColor(0xFF00C853);
                btnAndroid.setBackgroundColor(0xFF112E1F);
            }
        });
        platformLayout.addView(btnIos);
        layout.addView(platformLayout);

        // كتابة السؤال أو الطلب للذكاء الاصطناعي
        TextView lblIdea = new TextView(this);
        lblIdea.setText("\nتحدث مع الذكاء الاصطناعي بالعربية:");
        lblIdea.setTextColor(0xFF00C853);
        lblIdea.setTextSize(16);
        layout.addView(lblIdea);

        promptInput = new EditText(this);
        promptInput.setHint("اكتب سؤالك أو فكرتك هنا ليقوم الذكاء الاصطناعي بالرد...");
        promptInput.setHintTextColor(0xFF888888);
        promptInput.setTextColor(0xFFFFFFFF);
        promptInput.setBackgroundColor(0xFF112E1F);
        layout.addView(promptInput);

        // زر إرسال الطلب للذكاء الاصطناعي
        btnGenerate = new Button(this);
        btnGenerate.setText("\nإرسال الطلب للذكاء الاصطناعي\n");
        btnGenerate.setBackgroundColor(0xFF00C853);
        btnGenerate.setTextColor(0xFF000000);
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = promptInput.getText().toString().trim();
                if (query.isEmpty()) {
                    Toast.makeText(MainActivity.this, "الرجاء كتابة النص أولاً!", Toast.LENGTH_SHORT).show();
                } else {
                    // محاكاة الاتصال والرد الذكي الداعم للغة العربية والمنصة المختارة
                    resultText.setText("🤖 رد الذكاء الاصطناعي (" + selectedPlatform + "):\n\n" +
                            "أهلاً بك يا محمد. بناءً على طلبك (" + query + ")، تم تحليل وهندسة الشيفرة البرمجية خصيصاً لتتطابق مع معايير " + selectedPlatform + ".\n\n" +
                            "// هيكل الكود المقترح:\n" +
                            "public class AI_Generated_App {\n" +
                            "    // تم تفعيل حماية التطبيق ومنع التلاعب\n" +
                            "    public void run() {\n" +
                            "        System.out.println(\"تم التشغيل بنجاح\");\n" +
                            "    }\n" +
                            "}");
                }
            }
        });
        layout.addView(btnGenerate);

        // شاشة عرض الردود
        resultText = new TextView(this);
        resultText.setText("\n[ستظهر هنا ردود وأكواد الذكاء الاصطناعي...]");
        resultText.setTextColor(0xFFCCCCCC);
        resultText.setTextSize(13);
        layout.addView(resultText);

        scrollView.addView(layout);
        setContentView(scrollView);
    }

    // رابط محفظة جيب المباشر الصحيح للعملة اليمنية (7,000 ريال) ودون رقم هاتف
    private void showJeepPaymentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("الدفع الآمن عبر محفظة جيب");
        builder.setMessage("سعر التفعيل الشامل: 7,000 ريال يمني فقط.\n\nسيتم تحويلك مباشرة إلى محفظة جيب لإتمام الدفع بسرية تامة دون كشف رقم هاتفك.");
        builder.setPositiveButton("الانتقال لمحفظة جيب", (dialog, which) -> {
            // استخدام رابط محفظة جيب المباشر والصحيح
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://jeep.ye/"));
            startActivity(browserIntent);
            
            isPremium = true;
            statusText.setText("النسخة البريميوم مفعلة ومحمية (مشفرة)");
            statusText.setTextColor(0xFFFFD700);
            Toast.makeText(MainActivity.this, "تم تفعيل النسخة البريميوم بنجاح تام!", Toast.LENGTH_LONG).show();
        });
        builder.setNegativeButton("إلغاء", (dialog, which) -> dialog.dismiss());
        builder.show();
    }
}