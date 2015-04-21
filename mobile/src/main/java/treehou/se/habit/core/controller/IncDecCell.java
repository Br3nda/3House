package treehou.se.habit.core.controller;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

import treehou.se.habit.core.Item;

/**
 * Created by ibaton on 2014-11-08.
 */

@Table(name = "IncDecCell")
public class IncDecCell extends Model {

    @Column(name = "icon")
    public int icon;

    @Column(name = "Cell", onDelete = Column.ForeignKeyAction.CASCADE)
    public Cell cell;

    @Column(name = "type")
    public int type;

    @Column(name = "Item")
    public Item item;

    @Column(name = "value")
    public int value = 0;

    @Column(name = "min")
    public int min = 0;

    @Column(name = "max")
    public int max = 100;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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
