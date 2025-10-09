package net.ddns.mindustry.msgLog;

import arc.*;
import arc.util.*;
import mindustry.game.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.world.blocks.logic.*;
import mindustry.world.blocks.logic.MessageBlock.*;

public class MsgLog extends Plugin{
    //who needs config anyway, just hardcode stuff :3
    @Override
    public void init(){
        Events.on(EventType.ConfigEvent.class, e -> {
            if(e.tile instanceof MessageBuild && e.value instanceof String msg && e.player != null){
                Log.info("@ [@] edited a message: @", e.player.name, e.player.uuid(), msg);
            }
        });
        Events.on(EventType.BlockBuildEndEvent.class, e -> {
            if(e.config == null || !(e.tile.block() instanceof MessageBlock) || !e.unit.isPlayer()) return;
            if(e.config instanceof String msg){
                Player player = e.unit.getPlayer();
                if(player == null) return; //just in case
                Log.info("@ [@] placed a message with content @", player.name, player.uuid(), msg);
            }
        });
    }
}
