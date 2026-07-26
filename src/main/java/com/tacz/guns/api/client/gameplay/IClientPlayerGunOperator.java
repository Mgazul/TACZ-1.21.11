package com.tacz.guns.api.client.gameplay;

import com.tacz.guns.api.entity.ShootResult;
import com.tacz.guns.client.gameplay.LocalPlayerDataHolder;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 客户端枪械操纵者
 * 目前仅用于 LocalPlayer
 */

public interface IClientPlayerGunOperator {
    class OperatorRegistry {
        private static final Map<LocalPlayer, IClientPlayerGunOperator> OPERATOR_MAP = new ConcurrentHashMap<>();

        public static void register(LocalPlayer player, IClientPlayerGunOperator operator) {
            OPERATOR_MAP.put(player, operator);
        }

        public static void unregister(LocalPlayer player) {
            OPERATOR_MAP.remove(player);
        }

        public static IClientPlayerGunOperator get(LocalPlayer player) {
            return OPERATOR_MAP.get(player);
        }
    }

    /**
     * LocalPlayer 通过 Mixin 的方式实现了这个接口
     */
    static IClientPlayerGunOperator fromLocalPlayer(LocalPlayer player) {
        IClientPlayerGunOperator op = OperatorRegistry.get(player);
        if (op != null) return op;
        // Fallback to direct cast (works when Mixin applies correctly)
        return (IClientPlayerGunOperator) player;
    }

    /**
     * 检查玩家能否开火，并执行客户端开火逻辑。
     *
     * @return 返回开火的结果
     */
    ShootResult shoot();

    /**
     * 执行客户端切枪逻辑。
     */
    void draw(ItemStack lastItem);

    /**
     * 客户端手动换弹
     */
    void bolt();

    /**
     * 客户端换弹
     */
    void reload();

    /**
     * 客户端检视
     */
    void inspect();

    /**
     * 客户端切换开火模式
     */
    void fireSelect();

    /**
     * 客户端瞄准
     */
    void aim(boolean isAim);

    /**
     * 客户端爬行
     */
    void crawl(boolean isCrawl);

    /**
     * 客户端近战（刺刀）
     */
    void melee();

    /**
     * 客户端是否处于瞄准状态
     */
    boolean isAim();

    /**
     * 是否爬行
     */
    boolean isCrawl();

    LocalPlayerDataHolder getDataHolder();

    /**
     * 客户端瞄准进度
     *
     * @return 0-1，1 代表开镜进度到 100%
     */
    float getClientAimingProgress(float partialTicks);

    /**
     * 客户端射击冷却时间
     */
    long getClientShootCoolDown();

    boolean isReadyToDraw();

    void resetDraw();
}
