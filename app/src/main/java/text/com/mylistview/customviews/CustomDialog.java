package text.com.mylistview.customviews;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.greenrobot.event.EventBus;
import text.com.mylistview.R;


public class CustomDialog extends Dialog {

	public CustomDialog(Context context, int theme) {
		super(context, theme);
	}

	public CustomDialog(Context context) {
		super(context);
	}

	/**
	 * Helper class for creating a custom dialog
	 */
	public static class Builder {

		private Context context;
		private String title;
		private String message;
		private String positiveButtonText;
		private String negativeButtonText;
		private boolean blInput;
		private int inputBgRes;

		private int positiveImage = 0;
		private View contentView;

		private int gravity = Gravity.CENTER;

		private DialogInterface.OnClickListener positiveButtonClickListener,
				negativeButtonClickListener;

		public Builder(Context context) {
			this.context = context;
		}


		public Builder setMessage(String message) {
			this.message = message;
			return this;
		}


		public Builder setMessage(int message) {
			this.message = (String) context.getText(message);
			return this;
		}

		public Builder setMessageGravity(int gravity){
			this.gravity = gravity;
			return this;
		}

		public Builder setTitle(int title) {
			this.title = (String) context.getText(title);
			return this;
		}


		public Builder setTitle(String title) {
			this.title = title;
			return this;
		}


		public Builder setContentView(View v) {
			this.contentView = v;
			return this;
		}

		public Builder setInput(boolean value){
			this.blInput = value;
			return this;
		}

		public Builder setInputBg(int resId){
			this.inputBgRes = resId;
			return this;
		}


		public Builder setPositiveButton(int positiveButtonText,
										 DialogInterface.OnClickListener listener) {
			this.positiveButtonText = (String) context
					.getText(positiveButtonText);
			this.positiveButtonClickListener = listener;
			return this;
		}


		public Builder setPositiveButton(int positiveButtonText,
										 int positiveImage, DialogInterface.OnClickListener listener) {
			this.positiveButtonText = (String) context
					.getText(positiveButtonText);
			this.positiveButtonClickListener = listener;
			this.positiveImage = positiveImage;
			return this;
		}


		public Builder setPositiveButton(String positiveButtonText,
										 DialogInterface.OnClickListener listener) {
			this.positiveButtonText = positiveButtonText;
			this.positiveButtonClickListener = listener;
			return this;
		}


		public Builder setPositiveButton(String positiveButtonText,
										 int positiveImage, DialogInterface.OnClickListener listener) {
			this.positiveButtonText = positiveButtonText;
			this.positiveButtonClickListener = listener;
			this.positiveImage = positiveImage;
			return this;
		}


		public Builder setNegativeButton(int negativeButtonText,
										 DialogInterface.OnClickListener listener) {
			this.negativeButtonText = (String) context
					.getText(negativeButtonText);
			this.negativeButtonClickListener = listener;
			return this;
		}


		public Builder setNegativeButton(String negativeButtonText,
										 DialogInterface.OnClickListener listener) {
			this.negativeButtonText = negativeButtonText;
			this.negativeButtonClickListener = listener;
			return this;
		}



		public CustomDialog create() {
			final LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			// instantiate the dialog with the custom Theme
			final CustomDialog dialog = new CustomDialog(context,
					R.style.Dialog);
			View layout = inflater.inflate(R.layout.common_widget_dialog, null);
			dialog.addContentView(layout, new LayoutParams(
					LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
			// set the dialog title
			((TextView) layout.findViewById(R.id.common_widget_dialog_title)).setText(title);
			final EditText inputView = ((EditText) layout.findViewById(R.id.common_widget_dialog_input));
			inputView.setPadding(20, 0, 20, 0);
			// set the confirm button
			if (positiveButtonText != null) {
				((Button) layout.findViewById(R.id.common_widget_dialog_positiveButton))
						.setText(positiveButtonText);
				if (positiveButtonClickListener != null) {
					((Button) layout.findViewById(R.id.common_widget_dialog_positiveButton))
							.setOnClickListener(new View.OnClickListener() {
								public void onClick(View v) {
									if (blInput){
										EventBus.getDefault().post(inputView);
									}
									positiveButtonClickListener.onClick(dialog,
											DialogInterface.BUTTON_POSITIVE);
								}
							});
					((Button) layout.findViewById(R.id.common_widget_dialog_positiveButton))
							.setCompoundDrawablesWithIntrinsicBounds(
									positiveImage, 0, 0, 0);
				}
			} else {
				// if no confirm button just set the visibility to GONE
				layout.findViewById(R.id.common_widget_dialog_positiveButton).setVisibility(
						View.GONE);
			}

			// set the cancel button
			if (negativeButtonText != null) {
				((Button) layout.findViewById(R.id.common_widget_dialog_negativeButton))
						.setText(negativeButtonText);
				if (negativeButtonClickListener != null) {
					((Button) layout.findViewById(R.id.common_widget_dialog_negativeButton))
							.setOnClickListener(new View.OnClickListener() {
								public void onClick(View v) {
									negativeButtonClickListener.onClick(dialog,
											DialogInterface.BUTTON_NEGATIVE);
								}
							});
				}
			} else {
				// if no confirm button just set the visibility to GONE
				layout.findViewById(R.id.common_widget_dialog_negativeButton).setVisibility(
						View.GONE);
			}
			if (title != null) {
				((TextView) layout.findViewById(R.id.common_widget_dialog_title)).setText(title);
			}else {
				((TextView) layout.findViewById(R.id.common_widget_dialog_title)).setText(R.string.common_widget_dialog_default_title);
			}
			// set the content message
			if (message != null) {
				((TextView) layout.findViewById(R.id.common_widget_dialog_message)).setText(message);
				((TextView) layout.findViewById(R.id.common_widget_dialog_message)).setGravity(gravity);
			}else if (blInput){
				((TextView) layout.findViewById(R.id.common_widget_dialog_message)).setVisibility(View.GONE);
				inputView.setBackgroundResource(inputBgRes);
				inputView.setVisibility(View.VISIBLE);
			}else if (contentView != null) {
				// if no message set
				// add the contentView to the dialog body
				((LinearLayout) layout.findViewById(R.id.common_widget_dialog_content))
						.removeAllViews();
				((LinearLayout) layout.findViewById(R.id.common_widget_dialog_content)).addView(
						contentView, new LayoutParams(
								LayoutParams.WRAP_CONTENT,
								LayoutParams.WRAP_CONTENT));
			}
			dialog.setContentView(layout);
			return dialog;
		}


	}

	public void bindTitle(String title) {
		TextView tvTitle = (TextView) findViewById(R.id.common_widget_dialog_title);
		tvTitle.setText(title);
	}

	public void bindMessage(CharSequence message) {
		TextView tvMessage = (TextView) findViewById(R.id.common_widget_dialog_message);
		tvMessage.setText(message);
		tvMessage.setMovementMethod(LinkMovementMethod.getInstance());
	}

	public void bindPositiveButton(String text) {
		Button btnPositive = (Button) findViewById(R.id.common_widget_dialog_positiveButton);
		btnPositive.setText(text);
	}

	public void bindNegativeButton(String text) {
		Button btnNegative = (Button) findViewById(R.id.common_widget_dialog_negativeButton);
		btnNegative.setText(text);
	}

	public void setPositiveButtonBackgroundRes(int res){
		Button btnPositive = (Button) findViewById(R.id.common_widget_dialog_positiveButton);
		btnPositive.setBackgroundResource(res);
	}

	public void setNegativeButtonBackgroundRes(int res){
		Button btnNegative = (Button) findViewById(R.id.common_widget_dialog_negativeButton);
		btnNegative.setBackgroundResource(res);
	}

	public void setPositiveButtonEnable(boolean blEnable){
		Button btnPositive = (Button) findViewById(R.id.common_widget_dialog_positiveButton);
		btnPositive.setEnabled(blEnable);
	}

}
