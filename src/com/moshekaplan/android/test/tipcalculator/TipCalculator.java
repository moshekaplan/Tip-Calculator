package com.moshekaplan.android.test.tipcalculator;

import java.util.Locale;

import com.moshekaplan.android.test.tipcalculator.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TipCalculator extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tip_calculator);
		
		// First attach the onClick for the button
		Button calculateButton = (Button) findViewById(R.id.calculate_button);
		calculateButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				calculateTip();
			}

		});
		// To make it easier, auto-update the result when the fields are changed
		TextWatcher textWatcher = new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				calculateTip();
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
			}
		};
		
		EditText bill_widget = (EditText) findViewById(R.id.bill_amount);
		bill_widget.addTextChangedListener(textWatcher);
		
		EditText percent_widget = (EditText) findViewById(R.id.gratuity_amount);
		percent_widget.addTextChangedListener(textWatcher);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_tip_calculator, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.about:
	            displayAbout();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	private void displayAbout() {
		AlertDialog.Builder  builder = new AlertDialog.Builder(this);
		builder.setMessage(R.string.about_message)
	       .setTitle(R.string.about);

		// 3. Get the AlertDialog from create()
		builder.setNeutralButton(R.string.ok, null);
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	public void calculateTip() {
		EditText bill_widget = (EditText) findViewById(R.id.bill_amount);
		TextView tip_widget = (TextView) findViewById(R.id.output);
		EditText percent_widget = (EditText) findViewById(R.id.gratuity_amount);
		try {
			double bill_amount = Double.valueOf(bill_widget.getText()
					.toString());
			double tip_percent = Double.valueOf(percent_widget.getText()
					.toString());
			double gratuity = bill_amount * 0.01 * tip_percent;
			String message = getText(R.string.tip_message) + String.format(Locale.US, "%.2f", gratuity);
			tip_widget.setText(message);
		} catch (NumberFormatException e) {
			tip_widget.setText(R.string.calculation_error);
		}
	}
	
}
