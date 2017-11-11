package com.sxw.loan.loanorder.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.sxw.loan.loanorder.R;

/**
 * Created by Sxw on 2017-08-28.
 */

public class UpdataDialog extends Dialog {
    public UpdataDialog(Context context) {
        super(context);
    }

    public static class Builder {
        private Context context;
        private String title;
        private OnClickListener leftButton;
        private OnClickListener rightButton;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setCancelButton(OnClickListener listener) {
            this.rightButton = listener;
            return this;
        }

        public Builder setConfirmButton(OnClickListener listener) {
            this.leftButton = listener;
            return this;
        }

        public IPDialog create() {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final IPDialog dialog = new IPDialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉title
            View layout = inflater.inflate(R.layout.dialog_updata, null);
//            dialog.addContentView(layout, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            ((TextView) layout.findViewById(R.id.IPDialogTitle)).setText(title);
            layout.findViewById(R.id.IPDialogLeftButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    leftButton.onClick(dialog,
                            DialogInterface.BUTTON_POSITIVE);
                }
            });
            layout.findViewById(R.id.IPDialogRightButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rightButton.onClick(dialog,
                            DialogInterface.BUTTON_NEGATIVE);
                }
            });
            dialog.setCancelable(false);

            dialog.setContentView(layout);
            return dialog;
        }

    }

}