package treehou.se.habit.ui.control.config.cells;

import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RemoteViews;

import treehou.se.habit.R;
import treehou.se.habit.core.controller.Cell;
import treehou.se.habit.core.controller.Controller;
import treehou.se.habit.ui.control.CellFactory;
import treehou.se.habit.ui.control.ControllerUtil;

/**
 * Created by ibaton on 2014-11-08.
 */
public class DefaultConfigCellBuilder implements CellFactory.CellBuilder {

    public View build(Context context, Controller controller, Cell cell){

        int[] pallete = ControllerUtil.generateColor(controller, cell);

        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(R.layout.cell_conf_button, null);
        rootView.setBackgroundColor(pallete[ControllerUtil.INDEX_BUTTON]);

        ImageButton imgView = (ImageButton) rootView.findViewById(R.id.img_icon_button);

        imgView.getBackground().setColorFilter(pallete[ControllerUtil.INDEX_BUTTON], PorterDuff.Mode.MULTIPLY);

        return rootView;
    }

    @Override
    public RemoteViews buildRemote(Context context, Controller controller, Cell cell) {
        return null;
    }
}
