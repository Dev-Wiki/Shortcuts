package net.devwiki.shortcuts;

import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button addBtn;
    private Button removeBtn;
    private ShortcutManager shortcutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addBtn = (Button) findViewById(R.id.add_btn);
        removeBtn = (Button) findViewById(R.id.remove_btn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addShortcut();
            }
        });
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeShortcut();
            }
        });

        shortcutManager = getSystemService(ShortcutManager.class);
    }

    private void addShortcut() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            List<ShortcutInfo> infoList = shortcutManager.getDynamicShortcuts();
            String shortcutId = null;
            for (ShortcutInfo shortcutInfo : infoList) {
                if (ShortcutsId.WEB_DEVWIKI.equals(shortcutInfo.getId())) {
                    shortcutId = shortcutInfo.getId();
                }
            }
            if (shortcutId == null) {
                ShortcutInfo shortcut = new ShortcutInfo.Builder(this, ShortcutsId.WEB_DEVWIKI)
                        .setShortLabel(getString(R.string.blog_short_label))
                        .setLongLabel(getString(R.string.blog_long_label))
                        .setIcon(Icon.createWithResource(this, R.drawable.ic_blog_logo))
                        .setIntent(new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.devwiki.net")))
                        .build();
                shortcutManager.addDynamicShortcuts(Arrays.asList(shortcut));
                Toast.makeText(this, R.string.add_shortcut_success, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, R.string.shortcut_already_exist, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.not_support_function, Toast.LENGTH_SHORT).show();
        }
    }

    private void removeShortcut() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            List<ShortcutInfo> infoList = shortcutManager.getDynamicShortcuts();
            String shortcutId = null;
            for (ShortcutInfo shortcutInfo : infoList) {
                if (ShortcutsId.WEB_DEVWIKI.equals(shortcutInfo.getId())) {
                    shortcutId = shortcutInfo.getId();
                }
            }
            if (shortcutId == null) {
                Toast.makeText(this, R.string.shortcut_not_exist, Toast.LENGTH_SHORT).show();
            } else {
                shortcutManager.removeDynamicShortcuts(Arrays.asList(shortcutId));
                Toast.makeText(this, R.string.remove_shortcut_success, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, R.string.not_support_function, Toast.LENGTH_SHORT).show();
        }
    }
}
