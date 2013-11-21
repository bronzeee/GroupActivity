package com.opticaline.groupactivity;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.widget.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 13-11-20.
 */
public class ContactFragment extends Fragment {
    /**
     * 获取库Phone表字段*
     */
    private static final String[] PHONES_PROJECTION = new String[]{
            Phone.DISPLAY_NAME, Phone.NUMBER, Phone.PHOTO_ID, Phone.CONTACT_ID};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_layout, container, false);

        List<Contact> names = new ArrayList<Contact>();
        ListView listView = (ListView) view.findViewById(R.id.contacts);
        ContentResolver resolver = getActivity().getContentResolver();
        Cursor phoneCursor = resolver.query(Phone.CONTENT_URI, PHONES_PROJECTION, null, null, null);
        if (phoneCursor != null) {
            while (phoneCursor.moveToNext()) {
                Contact contact = new Contact();
                contact.setName(phoneCursor.getString(0));
                contact.setNumber(phoneCursor.getString(1));
                names.add(contact);
            }
        }
        ArrayAdapter<Contact> adapter = new ArrayAdapter<Contact>(getActivity(), R.layout.contact_line, names) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LinearLayout linearLayout;
                if (convertView == null) {
                    linearLayout = new LinearLayout(getContext());
                    String inflater = Context.LAYOUT_INFLATER_SERVICE;
                    LayoutInflater vi = (LayoutInflater) getContext().getSystemService(inflater);
                    vi.inflate(R.layout.contact_line, linearLayout, true);
                } else {
                    linearLayout = (LinearLayout) convertView;
                }
                TextView textView = (TextView)linearLayout.findViewById(R.id.name);
                textView.setText(getItem(position).getName());
                return linearLayout;
            }
        };
        listView.setAdapter(adapter);
        phoneCursor.close();
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("info", "TestFragment-----onDestroy");
    }

    private class Contact {
        private String name;
        private String number;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }
    }
}
