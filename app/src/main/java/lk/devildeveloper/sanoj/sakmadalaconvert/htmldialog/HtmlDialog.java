package lk.devildeveloper.sanoj.sakmadalaconvert.htmldialog;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class HtmlDialog
{
    private FragmentManager mFragmentManager;
    private HtmlDialogListener mListener;
    private int mHtmlResId;
    private String mTitle;
    private boolean mShowNegativeButton;
    private String mNegativeButtonText;
    private boolean mShowPositiveButton;
    private String mPositiveButtonText;
    private boolean mCancelable = true;

    public HtmlDialog(FragmentManager fm)
    {
        FragmentTransaction ft = fm.beginTransaction();
        Fragment prev = fm.findFragmentByTag(HtmlDialogFragment.TAG_HTML_DIALOG_FRAGMENT);
        if (prev != null)
        {
            ft.remove(prev);
            ft.commit();
        }
        mFragmentManager = fm;
    }

    public void setListener(HtmlDialogListener listener)
    {
        mListener = listener;
    }
    public void setHtmlResId(int htmlResId)
    {
        mHtmlResId = htmlResId;
    }
    public void setTitle(String title)
    {
        mTitle = title;
    }
    public void setShowNegativeButton(boolean showNegativeButton)
    {
        mShowNegativeButton = showNegativeButton;
    }
    public void setNegativeButtonText(String negativeButtonText)
    {
        mNegativeButtonText = negativeButtonText;
    }
    public void setShowPositiveButton(boolean showPositiveButton)
    {
        mShowPositiveButton = showPositiveButton;
    }

    public void setPositiveButtonText(String positiveButtonText)
    {
        mPositiveButtonText = positiveButtonText;
    }
    public void setCancelable(boolean cancelable)
    {
        mCancelable = cancelable;
    }

    public void show()
    {
        HtmlDialogFragment dialogFragment =
                HtmlDialogFragment.newInstance(
                        mListener,
                        mHtmlResId,
                        mTitle,
                        mShowNegativeButton,
                        mNegativeButtonText,
                        mShowPositiveButton,
                        mPositiveButtonText,
                        mCancelable);

        dialogFragment.show(mFragmentManager,
                HtmlDialogFragment.TAG_HTML_DIALOG_FRAGMENT);
    }

    public static class Builder
    {
        private FragmentManager fm;

        private HtmlDialogListener listener;
        private int htmlResId;
        private String title;
        private boolean showNegativeButton;
        private String negativeButtonText;
        private boolean showPositiveButton;
        private String positiveButtonText;
        private boolean cancelable = true;

        public Builder(FragmentManager fm)
        {
            this.fm = fm;
        }
        public Builder setListener(HtmlDialogListener listener)
        {
            this.listener = listener;

            return this;
        }
        public Builder setHtmlResId(int htmlResId)
        {
            this.htmlResId = htmlResId;

            return this;
        }
        public Builder setTitle(String title)
        {
            this.title = title;

            return this;
        }
        public Builder setShowNegativeButton(boolean showNegativeButton)
        {
            this.showNegativeButton = showNegativeButton;

            return this;
        }
        public Builder setNegativeButtonText(String negativeButtonText)
        {
            this.negativeButtonText = negativeButtonText;

            return this;
        }
        public Builder setShowPositiveButton(boolean showPositiveButton)
        {
            this.showPositiveButton = showPositiveButton;

            return this;
        }
        public Builder setPositiveButtonText(String positiveButtonText)
        {
            this.positiveButtonText = positiveButtonText;

            return this;
        }
        public Builder setCancelable(boolean cancelable)
        {
            this.cancelable = cancelable;

            return this;
        }
        public HtmlDialog build()
        {
            HtmlDialog dialog = new HtmlDialog(fm);
            dialog.setListener(listener);
            dialog.setHtmlResId(htmlResId);
            dialog.setTitle(title);
            dialog.setShowNegativeButton(showNegativeButton);
            dialog.setNegativeButtonText(negativeButtonText);
            dialog.setShowPositiveButton(showPositiveButton);
            dialog.setPositiveButtonText(positiveButtonText);
            dialog.setCancelable(cancelable);

            return dialog;
        }
    }
}
