package com.yucun.mastercardsforkids.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.simplify.android.sdk.Card;
import com.simplify.android.sdk.CardToken;
import com.simplify.android.sdk.Simplify;
import com.yucun.mastercardsforkids.R;

import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;

/**
 * Created by yucunli on 2015-09-26.
 */
public class EarnMoneyActivity extends AppCompatActivity{


    public static int MY_SCAN_REQUEST_CODE = 10;

    EditText card_number;
    EditText card_expire_date;
    CreditCard scanResult;
    Card card;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earn_money);

        card_number = (EditText) findViewById(R.id.card_number);
        card_expire_date = (EditText) findViewById(R.id.card_expire_date);

        Button bt = (Button) findViewById(R.id.submit);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // *******************************
                // need confirm dialog
                // *******************************
                requestCardToken();
            }
        });
    }

    public void onScanPress(View v) {
        Intent scanIntent = new Intent(this, CardIOActivity.class);

        // customize these values to suit your needs.
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true); // default: false
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false); // default: false
        scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false); // default: false

        // MY_SCAN_REQUEST_CODE is arbitrary and is only used within this activity.
        startActivityForResult(scanIntent, MY_SCAN_REQUEST_CODE);
    }

    private void requestCardToken() {

        if(scanResult == null){
            card = new Card();
            card.setNumber(card_number.getText().toString());
            String[] expire_date_array = card_expire_date.getText().toString().split("/");
            card.setExpMonth(expire_date_array[0]);
            card.setExpYear(expire_date_array[1]);

            card.setCvc("123");
            card.setAddressZip("10003");
        }

        Simplify.createCardToken(card, new CardToken.Callback() {
            @Override
            public void onSuccess(CardToken cardToken) {

                Log.e("Su", "Wow!");

            }

            @Override
            public void onError(Throwable throwable) {
                throwable.printStackTrace();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MY_SCAN_REQUEST_CODE) {
            //String resultDisplayStr;
            if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
                scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);
                card = new Card();

                // Never log a raw card number. Avoid displaying it, but if necessary use getFormattedCardNumber()
                //resultDisplayStr = "Card Number: " + scanResult.getRedactedCardNumber() + "\n";
                card_number.setText(scanResult.getRedactedCardNumber());
                card.setNumber(scanResult.cardNumber);

                // Do something with the raw number, e.g.:
                // myService.setCardNumber( scanResult.cardNumber );

                if (scanResult.isExpiryValid()) {
                    //resultDisplayStr += "Expiration Date: " + scanResult.expiryMonth + "/" + scanResult.expiryYear + "\n";
                    card_expire_date.setText(scanResult.expiryMonth + "/" + scanResult.expiryYear);
                    card.setExpMonth(scanResult.expiryMonth+"");
                    card.setExpYear(scanResult.expiryYear - 2000 +"" );
                }

                if (scanResult.cvv != null) {
                    // Never log or display a CVV
                    //resultDisplayStr += "CVV has " + scanResult.cvv.length() + " digits.\n";
                }

                if (scanResult.postalCode != null) {
                    //resultDisplayStr += "Postal Code: " + scanResult.postalCode + "\n";
                }

                card.setCvc("123");
                card.setAddressZip("10003");

            }
            else {
                //resultDisplayStr = "Scan was canceled.";
            }
            // do something with resultDisplayStr, maybe display it in a textView
            // resultTextView.setText(resultStr);
        }
        // else handle other activity results

    }

}
