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

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    private Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addBtn = (Button) findViewById(R.id.add_btn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addShortcut();
            }
        });
    }

    private void addShortcut() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
            ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
            ShortcutInfo shortcut = new ShortcutInfo.Builder(this, ShortcutsId.WEB_DEVWIKI)
                    .setShortLabel(getString(R.string.blog_short_label))
                    .setLongLabel(getString(R.string.blog_long_label))
                    .setIcon(Icon.createWithResource(this, R.drawable.ic_blog_logo))
                    .setIntent(new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.devwiki.net")))
                    .build();
            shortcutManager.addDynamicShortcuts(Arrays.asList(shortcut));
        }
    }
}
