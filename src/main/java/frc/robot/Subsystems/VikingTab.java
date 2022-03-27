package frc.robot.Subsystems;

import java.util.ArrayList;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.util.sendable.Sendable;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VikingTab extends SubsystemBase{

    private ShuffleboardTab tab;
    private ArrayList<NetworkTableEntry> entries = new ArrayList<NetworkTableEntry>();

    public VikingTab(String tabToGet) {
        tab = Shuffleboard.getTab(tabToGet);
    }

    public void add(String key, Object defaultValue) {
        entries.add(tab.add(key, defaultValue).getEntry());
    }

    public void addSendable(Sendable s) {
        tab.add(s);
    }

    public void set(String key, Object value) {
        entries.get(getIndex(key)).setValue(value);
    }

    public double getDouble(String key) {
        return entries.get(getIndex(key)).getDouble(-1);
    }

    public boolean getBoolean(String key) {
        return entries.get(getIndex(key)).getBoolean(false);
    }

    public String getString(String key) {
        return entries.get(getIndex(key)).getString("Error 1");
    }

    public ShuffleboardTab getTab() {
        return tab;
    }

    public NetworkTableEntry getEntry(String key) {
        return entries.get(getIndex(key));
    }

    private int getIndex(String name) {
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).getName().matches(name)) {
                return i;
            }
        }
        return -1;
    }
}
