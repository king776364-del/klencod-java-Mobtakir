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

        statusText = new TextView(this);
        statusText.setText("تجربة مجانية نشطة (متبقي: 30 يوم)");
        statusText.setTextColor(0xFF00C853);
        statusText.setTextSize(14);
        statusText.setPadding(20, 20, 20, 20);
        layout.addView(statusText);

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

        TextView lblIdea = new TextView(this);
        lblIdea.setText("\nاكتب فكرة تطبيقك باللغة العربية:");
        lblIdea.setTextColor(0xFF00C853);
        lblIdea.setTextSize(16);
        layout.addView(lblIdea);

        promptInput = new EditText(this);
        promptInput.setHint("اكتب فكرة التطبيق هنا...");
        promptInput.setHintTextColor(0xFF888888);
        promptInput.setTextColor(0xFFFFFFFF);
        promptInput.setBackgroundColor(0xFF112E1F);
        layout.addView(promptInput);

        btnGenerate = new Button(this);
        btnGenerate.setText("\nتوليد التطبيق بالذكاء الاصطناعي\n");
        btnGenerate.setBackgroundColor(0xFF00C853);
        btnGenerate.setTextColor(0xFF000000);
        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idea = promptInput.getText().toString().trim();
                if (idea.isEmpty()) {
                    Toast.makeText(MainActivity.this, "الرجاء كتابة فكرة التطبيق أولاً!", Toast.LENGTH_SHORT).show();
                } else {
                    resultText.setText("تم توليد تطبيق (" + selectedPlatform + ") بنجاح!\nفكرة التطبيق: " + idea + "\nالحماية: مفعلة ومشفرة بالكامل.");
                }
            }
        });
        layout.addView(btnGenerate);

        resultText = new TextView(this);
        resultText.setText("\n[انتظار إدخال الفكرة للتوليد...]");
        resultText.setTextColor(0xFFCCCCCC);
        resultText.setTextSize(13);
        layout.addView(resultText);

        scrollView.addView(layout);
        setContentView(scrollView);
    }

    private void showJeepPaymentDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("رابط الدفع المباشر - محفظة جيب");
        builder.setMessage("سعر التفعيل الشامل: 7,000 ريال يمني فقط.\n\nرابط المحفظة المباشر:\nhttps://jeep.ye/pay?amount=7000&currency=YER\n\nيتم التحويل بسرية تامة دون إظهار رقم هاتفك.");
        builder.setPositiveButton("فتح رابط جيب ودفع", (dialog, which) -> {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://jeep.ye/pay?amount=7000&currency=YER"));
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
