package treehou.se.habit.ui.control.config.cells;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RemoteViews;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.Realm;
import treehou.se.habit.R;
import treehou.se.habit.core.db.model.controller.CellDB;
import treehou.se.habit.core.db.model.controller.ControllerDB;
import treehou.se.habit.core.db.model.controller.VoiceCellDB;
import treehou.se.habit.util.Util;
import treehou.se.habit.ui.control.CellFactory;
import treehou.se.habit.ui.control.ControllerUtil;

public class VoiceConfigCellBuilder implements CellFactory.CellBuilder {

    private static final String TAG = "VoiceConfigCellBuilder";

    @Bind(R.id.img_icon_button) ImageButton imgIcon;

    public View build(Context context, ControllerDB controller, CellDB cell){
        LayoutInflater inflater = LayoutInflater.from(context);
        View cellView = inflater.inflate(R.layout.cell_conf_button, null);
        ButterKnife.bind(this, cellView);

        Realm realm = Realm.getDefaultInstance();
        VoiceCellDB voiceCell = VoiceCellDB.getCell(realm, cell);
        realm.close();

        int[] pallete = ControllerUtil.generateColor(controller, cell);

        cellView.setBackgroundColor(pallete[ControllerUtil.INDEX_BUTTON]);

        Drawable icon = Util.getIconDrawable(context, voiceCell.getIcon());
        imgIcon.getBackground().setColorFilter(pallete[ControllerUtil.INDEX_BUTTON], PorterDuff.Mode.MULTIPLY);
        if(icon != null){
            imgIcon.setImageDrawable(icon);
        }

        ButterKnife.unbind(toString());

        return cellView;
    }

    @Override
    public RemoteViews buildRemote(Context context, ControllerDB controller, CellDB cell) {
        return null;
    }
}
