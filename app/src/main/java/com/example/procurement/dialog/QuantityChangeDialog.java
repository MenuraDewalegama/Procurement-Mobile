package com.example.procurement.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.procurement.R;

public class QuantityChangeDialog extends Dialog {

    private IOnOkClickListener okClickListener;
    private TextView header, value, product_name;
    private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
    private Button btnPointFive;
    private Button btn_point_two_five, btn_dot;
    private boolean showPointFive;
    private boolean pointerValueClicked = false;
    private ImageButton btnOk;

    private String name = "";
    private int qty = 0;
    private double price = 0;


    public QuantityChangeDialog(Context context, boolean showPointFive, IOnOkClickListener okClickListener, String name, int qty, double price) {
        super(context, true, null);
//      this.context = context;
        this.showPointFive = showPointFive;
        this.okClickListener = okClickListener;

        this.name = name;
        this.qty = qty;
        this.price = price;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quantity_change_dialog);

        header = (TextView) findViewById(R.id.dialog_keypad_header_tv);
        value = (TextView) findViewById(R.id.dialog_keypad_value);
        product_name = findViewById(R.id.product_name_display);

        product_name.setText("" + this.name);
        value.setText("" + this.qty);

        ImageButton backspace = (ImageButton) findViewById(R.id.dialog_keypad_img_back);
        btnOk = (ImageButton) findViewById(R.id.btn_ok);
        ImageButton btnCancel = (ImageButton) findViewById(R.id.btn_cncl);

        btn0 = (Button) findViewById(R.id.btn_0);
        btn1 = (Button) findViewById(R.id.btn_1);
        btn2 = (Button) findViewById(R.id.btn_2);
        btn3 = (Button) findViewById(R.id.btn_3);
        btn4 = (Button) findViewById(R.id.btn_4);
        btn5 = (Button) findViewById(R.id.btn_5);
        btn6 = (Button) findViewById(R.id.btn_6);
        btn7 = (Button) findViewById(R.id.btn_7);
        btn8 = (Button) findViewById(R.id.btn_8);
        btn9 = (Button) findViewById(R.id.btn_9);

        btnPointFive = (Button) findViewById(R.id.btn_point_five);
        btn_point_two_five = (Button) findViewById(R.id.btn_point_two_five);

        btn_dot = (Button) findViewById(R.id.btn_dot);
        btn_dot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!(value.getText().toString().isEmpty()) && !(value.getText().toString().contains(".")) && !(value.getText().toString().equals("0"))) {
                    value.setText(value.getText().toString() + ".");
                }
            }
        });

//        if (pref.getSpecialDiscountStatus().equals("1")) {
//            value.setFilters(new InputFilter[]{new DecimalDigitsFilter(5, 2)});
//            value.setText("0.00");
//            btn_dot.setVisibility(View.VISIBLE);
//
//
//        } else {
//            value.setText("0");
//            btn_dot.setVisibility(View.INVISIBLE);
//        }

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = value.getText().toString();
               /* if (!val.equals("0")) {
                    val += "0";
                }*/

                if (val.equals("0") || val.equals("0.00")) {
                    //val = "0";
                } else {
                    val += "0";
                }


                value.setText(val);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = value.getText().toString();
                if (val.equals("0") || val.equals("0.00")) {
                    val = "1";
                } else {
                    val += "1";
                }
                value.setText(val);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = value.getText().toString();
                if (val.equals("0") || val.equals("0.00")) {
                    val = "2";
                } else {
                    val += "2";
                }
                value.setText(val);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = value.getText().toString();
                if (val.equals("0") || val.equals("0.00")) {
                    val = "3";
                } else {
                    val += "3";
                }
                value.setText(val);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = value.getText().toString();
                if (val.equals("0") || val.equals("0.00")) {
                    val = "4";
                } else {
                    val += "4";
                }
                value.setText(val);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = value.getText().toString();
                if (val.equals("0") || val.equals("0.00")) {
                    val = "5";
                } else {
                    val += "5";
                }
                value.setText(val);
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = value.getText().toString();
                if (val.equals("0") || val.equals("0.00")) {
                    val = "6";
                } else {
                    val += "6";
                }
                value.setText(val);
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = value.getText().toString();
                if (val.equals("0") || val.equals("0.00")) {
                    val = "7";
                } else {
                    val += "7";
                }
                value.setText(val);
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = value.getText().toString();
                if (val.equals("0") || val.equals("0.00")) {
                    val = "8";
                } else {
                    val += "8";
                }
                value.setText(val);
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String val = value.getText().toString();
                if (val.equals("0") || val.equals("0.00")) {
                    val = "9";
                } else {
                    val += "9";
                }
                value.setText(val);
            }
        });

        btnPointFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!pointerValueClicked) {
                    String val = value.getText().toString();
                    if (val.equals("0")) {
                        val = "0.5";
                    } else {
                        val += ".5";
                    }
                    value.setText(val);

                    pointerValueClicked = true;

                    btnPointFive.setVisibility(View.INVISIBLE);
                    btnPointFive.setEnabled(false);

                    btn_point_two_five.setVisibility(View.INVISIBLE);
                    btn_point_two_five.setEnabled(false);

                    btn0.setEnabled(false);
                    btn1.setEnabled(false);
                    btn2.setEnabled(false);
                    btn3.setEnabled(false);
                    btn4.setEnabled(false);
                    btn5.setEnabled(false);
                    btn6.setEnabled(false);
                    btn7.setEnabled(false);
                    btn8.setEnabled(false);
                    btn9.setEnabled(false);
                }
            }
        });

        btn_point_two_five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!pointerValueClicked) {
                    String val = value.getText().toString();
                    if (val.equals("0")) {
                        val = "0.25";
                    } else {
                        val += ".25";
                    }
                    value.setText(val);

                    pointerValueClicked = true;

                    btnPointFive.setVisibility(View.INVISIBLE);
                    btnPointFive.setEnabled(false);

                    btn_point_two_five.setVisibility(View.INVISIBLE);
                    btn_point_two_five.setEnabled(false);

                    btn0.setEnabled(false);
                    btn1.setEnabled(false);
                    btn2.setEnabled(false);
                    btn3.setEnabled(false);
                    btn4.setEnabled(false);
                    btn5.setEnabled(false);
                    btn6.setEnabled(false);
                    btn7.setEnabled(false);
                    btn8.setEnabled(false);
                    btn9.setEnabled(false);
                }
            }
        });

        backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pointerValueClicked) {
                    String val = value.getText().toString();
                    String[] array = val.split("\\.");
                    String newVal = array[0];
                    value.setText(newVal);
                    pointerValueClicked = false;

                    btnPointFive.setVisibility(View.VISIBLE);
                    btnPointFive.setEnabled(true);

                    btn_point_two_five.setVisibility(View.VISIBLE);
                    btn_point_two_five.setEnabled(true);

                    btn0.setEnabled(true);
                    btn1.setEnabled(true);
                    btn2.setEnabled(true);
                    btn3.setEnabled(true);
                    btn4.setEnabled(true);
                    btn5.setEnabled(true);
                    btn6.setEnabled(true);
                    btn7.setEnabled(true);
                    btn8.setEnabled(true);
                    btn9.setEnabled(true);

                } else {
                    String val = value.getText().toString();
                    if (!val.equals("0")) {
                        if (val.length() == 1) {
                            value.setText("0");
                        } else {
                            String newTxt = val.substring(0, val.length() - 1);
                            value.setText(newTxt);
                        }
                    }
                }
            }
        });

        backspace.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                value.setText("0");
                return true;
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                okClickListener.okClicked(Double.parseDouble(value.getText().toString()));
                dismiss();
                qty = Integer.parseInt(value.getText().toString());

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCancelClicked();
            }
        });


    }
    public void onCancelClicked() {
        dismiss();
    }

    public interface IOnOkClickListener {
        void okClicked(double value);
    }
}
