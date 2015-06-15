package treehou.se.habit.core.db.controller;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import treehou.se.habit.core.db.ItemDB;

@Table(name = "SliderCells")
public class SliderCellDB extends Model {

    public static final int TYPE_MAX = 0;
    public static final int TYPE_MIN = 1;
    public static final int TYPE_SLIDER = 2;
    public static final int TYPE_CHART = 3;

    @Column(name = "iconName")
    public String icon;

    @Column(name = "Cell", onDelete = Column.ForeignKeyAction.CASCADE)
    public CellDB cell;

    @Column(name = "type")
    public int type;

    @Column(name = "Item")
    public ItemDB item;

    @Column(name = "min")
    public int min = 0;

    @Column(name = "max")
    public int max = 100;

    public ItemDB getItem() {
        return item;
    }

    public void setItem(ItemDB item) {
        this.item = item;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public CellDB getCell() {
        return cell;
    }

    public void setCell(CellDB cell) {
        this.cell = cell;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMin() {
        return min;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMax() {
        return max;
    }
}
