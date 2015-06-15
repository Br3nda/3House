package treehou.se.habit.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mattyork.colours.Colour;
import com.mikepenz.community_material_typeface_library.CommunityMaterial;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.mikepenz.iconics.typeface.FontAwesome;
import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.octicons_typeface_library.Octicons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import treehou.se.habit.R;
import treehou.se.habit.core.ItemDeserializer;
import treehou.se.habit.core.Sitemap;
import treehou.se.habit.core.SitemapDeserializer;
import treehou.se.habit.core.Widget;
import treehou.se.habit.core.WidgetDeserializer;
import treehou.se.habit.core.WidgetMappingDeserializer;
import treehou.se.habit.core.db.ItemDB;
import treehou.se.habit.core.db.settings.WidgetSettingsDB;
import treehou.se.habit.ui.control.IconAdapter;

public class Util {

    private final static Map<String, IIcon> CELL_ICON_MAP = new HashMap<>();
    private final static List<IIcon> CELL_ICONS = new ArrayList<>();
    static {
        CELL_ICONS.addAll(Arrays.asList(CommunityMaterial.Icon.values()));
        CELL_ICONS.addAll(Arrays.asList(GoogleMaterial.Icon.values()));
        CELL_ICONS.addAll(Arrays.asList(Octicons.Icon.values()));
        CELL_ICONS.addAll(Arrays.asList(FontAwesome.Icon.values()));

        for(IIcon icon : CELL_ICONS){
            CELL_ICON_MAP.put(icon.getName(), icon);
        }
    }

    public enum IconCategory {
        SENSORS, MEDIA, COMMANDS, ALL
    }

    public final static Map<IconCategory, List<IIcon>> CAT_ICONS = new HashMap<>();
    static {
        List<IIcon> sensors = new ArrayList<>();
        sensors.add(CommunityMaterial.Icon.cmd_alarm);
        sensors.add(CommunityMaterial.Icon.cmd_alarm_plus);
        sensors.add(CommunityMaterial.Icon.cmd_alert);
        sensors.add(CommunityMaterial.Icon.cmd_bell);
        sensors.add(CommunityMaterial.Icon.cmd_bell_off);
        sensors.add(CommunityMaterial.Icon.cmd_bell_ring);
        sensors.add(CommunityMaterial.Icon.cmd_brightness_5);
        sensors.add(CommunityMaterial.Icon.cmd_brightness_6);
        sensors.add(CommunityMaterial.Icon.cmd_brightness_7);
        CAT_ICONS.put(IconCategory.SENSORS, sensors);

        List<IIcon> media = new ArrayList<>();
        media.add(CommunityMaterial.Icon.cmd_play);
        media.add(CommunityMaterial.Icon.cmd_pause);
        media.add(CommunityMaterial.Icon.cmd_stop);
        media.add(CommunityMaterial.Icon.cmd_forward);
        media.add(CommunityMaterial.Icon.cmd_rewind);
        media.add(CommunityMaterial.Icon.cmd_skip_next);
        media.add(CommunityMaterial.Icon.cmd_skip_previous);
        media.add(CommunityMaterial.Icon.cmd_microphone_off);
        media.add(CommunityMaterial.Icon.cmd_microphone);
        media.add(CommunityMaterial.Icon.cmd_microphone_off);
        media.add(CommunityMaterial.Icon.cmd_volume_off);
        media.add(CommunityMaterial.Icon.cmd_volume_low);
        media.add(CommunityMaterial.Icon.cmd_volume_medium);
        media.add(CommunityMaterial.Icon.cmd_volume_high);
        CAT_ICONS.put(IconCategory.MEDIA, media);

        List<IIcon> commands = new ArrayList<>();
        commands.add(CommunityMaterial.Icon.cmd_airplane);
        commands.add(CommunityMaterial.Icon.cmd_airplane_off);
        commands.add(CommunityMaterial.Icon.cmd_bell_ring);
        commands.add(CommunityMaterial.Icon.cmd_lock);
        commands.add(CommunityMaterial.Icon.cmd_lock_open);
        commands.add(CommunityMaterial.Icon.cmd_power);
        commands.add(CommunityMaterial.Icon.cmd_coffee);
        commands.add(CommunityMaterial.Icon.cmd_beer);
        CAT_ICONS.put(IconCategory.COMMANDS, commands);

        CAT_ICONS.put(IconCategory.ALL, getIcons());
    }

    /**
     * Get a shallow copy of all available icons.
     *
     * @return list of icons.
     */
    public static List<IIcon> getIcons(){
        return new ArrayList<>(CELL_ICONS);
    }

    /**
     * Get icon from icon name.
     *
     * @param value name of icon
     * @return Icon coresponding to the name. Null if no match found
     */
    public static IIcon getIcon(String value){
        IIcon icon = CELL_ICON_MAP.get(value);

        return icon;
    }

    /**
     * Get bitmap for icon based on icon name
     *
     * @param context
     * @param value icon name
     * @return bitmap for icon. Null if no bitmap found
     */
    public static Bitmap getIconBitmap(Context context, String value){
        IconicsDrawable drawable = getIconDrawable(context, value);
        if(drawable == null){
            return null;
        }

        return drawable.toBitmap();
    }

    /**
     * TODO move to dialog fragment
     * Create select icon dialog.
     *
     * @param context
     * @param listener triggers when icon is selected
     */
    public static void crateIconSelected(Context context, final IconAdapter.IconSelectListener listener){
        final AppCompatDialog dialog = new AppCompatDialog(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.icon_picker, null);
        RecyclerView iconList = (RecyclerView) view.findViewById(R.id.list);
        iconList.setItemAnimator(new DefaultItemAnimator());
        iconList.setLayoutManager(new GridLayoutManager(context, 4));
        IconAdapter adapter = new IconAdapter(context);

        adapter.setIconSelectListener(new IconAdapter.IconSelectListener() {
            @Override
            public void iconSelected(IIcon icon) {
                listener.iconSelected(icon);
                dialog.dismiss();
            }
        });
        iconList.setAdapter(adapter);

        dialog.setContentView(view);
        dialog.show();
    }

    /**
     * Get drawable for icon.
     *
     * @param context
     * @param value name of icon
     * @return drawable for icon. Null if not found
     */
    public static IconicsDrawable getIconDrawable(Context context, String value){
        IIcon icon = Util.getIcon(value);
        if(icon == null){
            return null;
        }
        IconicsDrawable drawableIcon = new IconicsDrawable(context, icon).color(Color.BLACK).sizeDp(24);

        return drawableIcon;
    }

    public static int getBackground(Context context, Bitmap bitmap){
        return getBackground(context, bitmap, WidgetSettingsDB.MUTED_COLOR);
    }

    public static int getBackground(Context context, Bitmap bitmap, int type){
        if(type == WidgetSettingsDB.MUTED_COLOR) {
            return Palette.from(bitmap).generate().getMutedColor(context.getResources().getColor(R.color.image_background));
        }else if(type == WidgetSettingsDB.LIGHT_MUTED_COLOR) {
            return Palette.from(bitmap).generate().getLightMutedColor(context.getResources().getColor(R.color.image_background));
        }else if(type == WidgetSettingsDB.DARK_MUTED_COLOR) {
            return Palette.from(bitmap).generate().getDarkMutedColor(context.getResources().getColor(R.color.image_background));
        }else if(type == WidgetSettingsDB.VIBRANT_COLOR) {
            return Palette.from(bitmap).generate().getVibrantColor(context.getResources().getColor(R.color.image_background));
        }else if(type == WidgetSettingsDB.LIGHT_VIBRANT_COLOR) {
            return Palette.from(bitmap).generate().getLightVibrantColor(context.getResources().getColor(R.color.image_background));
        }else if(type == WidgetSettingsDB.DARK_VIBRANT_COLOR) {
            return Palette.from(bitmap).generate().getDarkVibrantColor(context.getResources().getColor(R.color.image_background));
        }else {
            return Palette.from(bitmap).generate().getMutedColor(context.getResources().getColor(R.color.image_background));
        }
    }

    public static float toPercentage(int percentage){
        return ((float)percentage)/100;
    }

    public static int[] generatePallete(int color){
        return Colour.colorSchemeOfType(color, Colour.ColorScheme.ColorSchemeAnalagous);
    }

    public static Gson gson = null;

    public synchronized static Gson createGsonBuilder(){

        if (gson == null) {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.registerTypeAdapter(new TypeToken<List<Widget>>() {}.getType(), new WidgetDeserializer());
            gsonBuilder.registerTypeAdapter(new TypeToken<List<Sitemap>>() {}.getType(), new SitemapDeserializer());
            gsonBuilder.registerTypeAdapter(new TypeToken<List<Widget.Mapping>>() {}.getType(), new WidgetMappingDeserializer());
            gsonBuilder.registerTypeAdapter(ItemDB.class, new ItemDeserializer());
            gson = gsonBuilder.create();
        }

        return gson;
    }
}
