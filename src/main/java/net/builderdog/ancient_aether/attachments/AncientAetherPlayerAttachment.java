package net.builderdog.ancient_aether.attachments;

import com.aetherteam.nitrogen.attachment.INBTSynchable;
import com.aetherteam.nitrogen.network.BasePacket;
import net.builderdog.ancient_aether.item.EquipmentUtil;
import net.minecraft.world.entity.player.Player;
import org.apache.commons.lang3.tuple.Triple;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class AncientAetherPlayerAttachment implements INBTSynchable {
    private int aeronauticDartCount;
    private int removeAeronauticDartTime;

    private int wingRotationO;
    private int wingRotation;
    private static final int FLIGHT_TIMER_MAX = 52;
    private static final float FLIGHT_MODIFIER_MAX = 15.0F;
    private int flightTimer;
    private float flightModifier = 1.0F;

    private final Map<String, Triple<Type, Consumer<Object>, Supplier<Object>>> synchableFunctions = Map.ofEntries(
            Map.entry("setAeronauticCount", Triple.of(Type.INT, (object) -> setAeronauticDartCount((int) object), this::getAeronauticDartCount))
    );

    public AncientAetherPlayerAttachment() {
    }

    @Override
    public Map<String, Triple<Type, Consumer<Object>, Supplier<Object>>> getSynchableFunctions() {
        return null;
    }

    @Override
    public BasePacket getSyncPacket(int entityID, String key, Type type, Object value) {
        return null;
    }

    public void onUpdate(Player player) {
        handleRemoveDarts(player);
        handleWingRotation(player);
    }

    private void handleRemoveDarts(Player player) {
        if (!player.level().isClientSide()) {
            if (getAeronauticDartCount() > 0) {
                if (removeAeronauticDartTime <= 0) {
                    removeAeronauticDartTime = 20 * (30 - getAeronauticDartCount());
                }

                --removeAeronauticDartTime;
                if (removeAeronauticDartTime <= 0) {
                    setSynched(player.getId(), INBTSynchable.Direction.CLIENT, "setAeronauticDartCount", getAeronauticDartCount() - 1);
                }
            }
        }
    }

    private void handleWingRotation(Player player) {
        if (player.level().isClientSide()) {
            wingRotationO = getWingRotation();
            if (EquipmentUtil.hasFullValkyrumSet(player)) {
                wingRotation = player.tickCount;
            } else {
                wingRotation = 0;
            }
        }
    }

    public void setAeronauticDartCount(int count) {
        aeronauticDartCount = count;
    }

    public int getAeronauticDartCount() {
        return aeronauticDartCount;
    }

    public int getWingRotationO() {
        return wingRotationO;
    }

    public int getWingRotation() {
        return wingRotation;
    }

    public int getFlightTimerMax() {
        return FLIGHT_TIMER_MAX;
    }

    public float getFlightModifierMax() {
        return FLIGHT_MODIFIER_MAX;
    }

    public void setFlightTimer(int timer) {
        flightTimer = timer;
    }

    public int getFlightTimer() {
        return flightTimer;
    }

    public void setFlightModifier(float modifier) {
        flightModifier = modifier;
    }

    public float getFlightModifier() {
        return flightModifier;
    }
}