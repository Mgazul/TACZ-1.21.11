package com.tacz.guns.init;

import com.google.common.collect.ImmutableMap;
import com.tacz.guns.GunMod;
import com.tacz.guns.api.item.gun.GunItemManager;
import com.tacz.guns.item.*;
import com.tacz.guns.item.gun.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegisterEvent;

import java.util.*;

@EventBusSubscriber
public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(GunMod.MOD_ID);

    // ===== 通用物品（向后兼容） =====
    public static DeferredItem<ModernKineticGunItem> MODERN_KINETIC_GUN = ITEMS.registerItem("modern_kinetic_gun", ModernKineticGunItem::new, p -> p.stacksTo(1));
    public static DeferredItem<Item> AMMO = ITEMS.registerItem("ammo", AmmoItem::new);
    public static DeferredItem<AttachmentItem> ATTACHMENT = ITEMS.registerItem("attachment", AttachmentItem::new, p -> p.stacksTo(1));
    public static DeferredItem<Item> AMMO_BOX = ITEMS.registerItem("ammo_box", AmmoBoxItem::new, p -> p.stacksTo(1));

    // ===== 每把枪独立 Item =====
    public static DeferredItem<Aa12> AA12 = ITEMS.registerItem("aa12", p -> new Aa12(p), p -> p.stacksTo(1));
    public static DeferredItem<AiAwp> AI_AWP = ITEMS.registerItem("ai_awp", p -> new AiAwp(p), p -> p.stacksTo(1));
    public static DeferredItem<Ak47> AK47 = ITEMS.registerItem("ak47", p -> new Ak47(p), p -> p.stacksTo(1));
    public static DeferredItem<Aug> AUG = ITEMS.registerItem("aug", p -> new Aug(p), p -> p.stacksTo(1));
    public static DeferredItem<B93r> B93R = ITEMS.registerItem("b93r", p -> new B93r(p), p -> p.stacksTo(1));
    public static DeferredItem<Cz75> CZ75 = ITEMS.registerItem("cz75", p -> new Cz75(p), p -> p.stacksTo(1));
    public static DeferredItem<DbLong> DB_LONG = ITEMS.registerItem("db_long", p -> new DbLong(p), p -> p.stacksTo(1));
    public static DeferredItem<DbShort> DB_SHORT = ITEMS.registerItem("db_short", p -> new DbShort(p), p -> p.stacksTo(1));
    public static DeferredItem<Deagle> DEAGLE = ITEMS.registerItem("deagle", p -> new Deagle(p), p -> p.stacksTo(1));
    public static DeferredItem<DeagleGolden> DEAGLE_GOLDEN = ITEMS.registerItem("deagle_golden", p -> new DeagleGolden(p), p -> p.stacksTo(1));
    public static DeferredItem<FnEvolys> FN_EVOLYS = ITEMS.registerItem("fn_evolys", p -> new FnEvolys(p), p -> p.stacksTo(1));
    public static DeferredItem<FnFal> FN_FAL = ITEMS.registerItem("fn_fal", p -> new FnFal(p), p -> p.stacksTo(1));
    public static DeferredItem<G36k> G36K = ITEMS.registerItem("g36k", p -> new G36k(p), p -> p.stacksTo(1));
    public static DeferredItem<Glock17> GLOCK_17 = ITEMS.registerItem("glock_17", p -> new Glock17(p), p -> p.stacksTo(1));
    public static DeferredItem<Hk416d> HK416D = ITEMS.registerItem("hk416d", p -> new Hk416d(p), p -> p.stacksTo(1));
    public static DeferredItem<HkG3> HK_G3 = ITEMS.registerItem("hk_g3", p -> new HkG3(p), p -> p.stacksTo(1));
    public static DeferredItem<HkMp5a5> HK_MP5A5 = ITEMS.registerItem("hk_mp5a5", p -> new HkMp5a5(p), p -> p.stacksTo(1));
    public static DeferredItem<M1014> M1014 = ITEMS.registerItem("m1014", p -> new M1014(p), p -> p.stacksTo(1));
    public static DeferredItem<M107> M107 = ITEMS.registerItem("m107", p -> new M107(p), p -> p.stacksTo(1));
    public static DeferredItem<M16a1> M16A1 = ITEMS.registerItem("m16a1", p -> new M16a1(p), p -> p.stacksTo(1));
    public static DeferredItem<M16a4> M16A4 = ITEMS.registerItem("m16a4", p -> new M16a4(p), p -> p.stacksTo(1));
    public static DeferredItem<M1911> M1911 = ITEMS.registerItem("m1911", p -> new M1911(p), p -> p.stacksTo(1));
    public static DeferredItem<M249> M249 = ITEMS.registerItem("m249", p -> new M249(p), p -> p.stacksTo(1));
    public static DeferredItem<M320> M320 = ITEMS.registerItem("m320", p -> new M320(p), p -> p.stacksTo(1));
    public static DeferredItem<M4a1> M4A1 = ITEMS.registerItem("m4a1", p -> new M4a1(p), p -> p.stacksTo(1));
    public static DeferredItem<M700> M700 = ITEMS.registerItem("m700", p -> new M700(p), p -> p.stacksTo(1));
    public static DeferredItem<M870> M870 = ITEMS.registerItem("m870", p -> new M870(p), p -> p.stacksTo(1));
    public static DeferredItem<M95> M95 = ITEMS.registerItem("m95", p -> new M95(p), p -> p.stacksTo(1));
    public static DeferredItem<Minigun> MINIGUN = ITEMS.registerItem("minigun", p -> new Minigun(p), p -> p.stacksTo(1));
    public static DeferredItem<Mk14> MK14 = ITEMS.registerItem("mk14", p -> new Mk14(p), p -> p.stacksTo(1));
    public static DeferredItem<P320> P320 = ITEMS.registerItem("p320", p -> new P320(p), p -> p.stacksTo(1));
    public static DeferredItem<P90> P90 = ITEMS.registerItem("p90", p -> new P90(p), p -> p.stacksTo(1));
    public static DeferredItem<Qbz191> QBZ_191 = ITEMS.registerItem("qbz_191", p -> new Qbz191(p), p -> p.stacksTo(1));
    public static DeferredItem<Qbz95> QBZ_95 = ITEMS.registerItem("qbz_95", p -> new Qbz95(p), p -> p.stacksTo(1));
    public static DeferredItem<Rpg7> RPG7 = ITEMS.registerItem("rpg7", p -> new Rpg7(p), p -> p.stacksTo(1));
    public static DeferredItem<Rpk> RPK = ITEMS.registerItem("rpk", p -> new Rpk(p), p -> p.stacksTo(1));
    public static DeferredItem<ScarH> SCAR_H = ITEMS.registerItem("scar_h", p -> new ScarH(p), p -> p.stacksTo(1));
    public static DeferredItem<ScarL> SCAR_L = ITEMS.registerItem("scar_l", p -> new ScarL(p), p -> p.stacksTo(1));
    public static DeferredItem<SksTactical> SKS_TACTICAL = ITEMS.registerItem("sks_tactical", p -> new SksTactical(p), p -> p.stacksTo(1));
    public static DeferredItem<Spas12> SPAS_12 = ITEMS.registerItem("spas_12", p -> new Spas12(p), p -> p.stacksTo(1));
    public static DeferredItem<Spr15hb> SPR15HB = ITEMS.registerItem("spr15hb", p -> new Spr15hb(p), p -> p.stacksTo(1));
    public static DeferredItem<Springfield1873> SPRINGFIELD1873 = ITEMS.registerItem("springfield1873", p -> new Springfield1873(p), p -> p.stacksTo(1));
    public static DeferredItem<Timeless50> TIMELESS50 = ITEMS.registerItem("timeless50", p -> new Timeless50(p), p -> p.stacksTo(1));
    public static DeferredItem<Type81> TYPE_81 = ITEMS.registerItem("type_81", p -> new Type81(p), p -> p.stacksTo(1));
    public static DeferredItem<Ump45> UMP45 = ITEMS.registerItem("ump45", p -> new Ump45(p), p -> p.stacksTo(1));
    public static DeferredItem<Uzi> UZI = ITEMS.registerItem("uzi", p -> new Uzi(p), p -> p.stacksTo(1));
    public static DeferredItem<Vector45> VECTOR45 = ITEMS.registerItem("vector45", p -> new Vector45(p), p -> p.stacksTo(1));

    // ===== ID 列表（用于批量注册弹药和配件） =====
    private static final String[] AMMO_ID_LIST = {
        "12g","308","30_06","338","357mag","40mm","45_70","45acp","46x30","50ae","50bmg",
        "545x39","556x45","57x28","58x42","68x51fury","762x25","762x39","762x54","9mm","rpg_rocket"
    };
    private static final String[] ATTACHMENT_ID_LIST = {
        "ammo_mod_fmj","ammo_mod_he","ammo_mod_hp","ammo_mod_i","ammo_mod_slug",
        "bayonet_6h3","bayonet_m9","deagle_golden_long_barrel",
        "extended_mag_1","extended_mag_2","extended_mag_3",
        "grip_cobra","grip_cqr","grip_magpul_afg_2","grip_osovets_black","grip_rk0","grip_rk1_b25u",
        "grip_rk6","grip_se_5","grip_td","grip_vertical_military","grip_vertical_ranger","grip_vertical_talon",
        "laser_compact","laser_lopro","laser_nightstick","laser_peq15",
        "light_extended_mag_1","light_extended_mag_2","light_extended_mag_3",
        "muzzle_brake_cthulhu","muzzle_brake_cyclone_d2","muzzle_brake_mastiff_sg","muzzle_brake_pioneer",
        "muzzle_brake_timeless50","muzzle_brake_trex","muzzle_choke_sg","muzzle_compensator_trident","muzzle_duckbill_sg",
        "muzzle_silencer_knight_qd","muzzle_silencer_mirage","muzzle_silencer_phantom_s1",
        "muzzle_silencer_ptilopsis","muzzle_silencer_sg","muzzle_silencer_ursus","muzzle_silencer_vulture",
        "oem_stock_heavy","oem_stock_light","oem_stock_tactical",
        "scope_1873_6x","scope_acog_ta31","scope_aug_default","scope_contender","scope_elcan_4x",
        "scope_hamr","scope_lpvo_1_6","scope_mk5hd","scope_qmk152","scope_retro_2x","scope_standard_8x","scope_vudu",
        "shotgun_extended_mag_1","shotgun_extended_mag_2","shotgun_extended_mag_3",
        "sight_552","sight_acro_pistol","sight_acro_rifle","sight_coyote","sight_deltapoint_pistol",
        "sight_deltapoint_rifle","sight_exp3","sight_fastfire_pistol","sight_fastfire_rifle","sight_okp7",
        "sight_p90","sight_pk06_pistol","sight_pk06_rifle","sight_rmr_dot","sight_sro_dot","sight_srs_02",
        "sight_t1","sight_t2","sight_uh1",
        "sniper_extended_mag_1","sniper_extended_mag_2","sniper_extended_mag_3",
        "stock_ak12","stock_carbon_bone_c5","stock_heavy_spas_12","stock_hk_slim_line","stock_m4ss",
        "stock_militech_b5","stock_moe","stock_ripstock","stock_sba3","stock_tactical_ar","stock_tactical_spas_12"
    };

    // ===== 独立弹药 Item（用 AmmoItem 类，不建子类） =====
    private static final Map<String, DeferredItem<Item>> AMMO_ITEMS = initMap(AMMO_ID_LIST, id -> ITEMS.registerItem(id, AmmoItem::new));
    // ===== 独立配件 Item（用 AttachmentItem 类，不建子类） =====
    private static final Map<String, DeferredItem<AttachmentItem>> ATTACHMENT_ITEMS = initMap(ATTACHMENT_ID_LIST, id -> ITEMS.registerItem(id, AttachmentItem::new, p -> p.stacksTo(1)));

    private static <T extends Item> Map<String, DeferredItem<T>> initMap(String[] ids, java.util.function.Function<String, DeferredItem<T>> factory) {
        var builder = ImmutableMap.<String, DeferredItem<T>>builder();
        for (String id : ids) {
            builder.put(id, factory.apply(id));
        }
        return builder.build();
    }

    @SubscribeEvent
    public static void onItemRegister(RegisterEvent event) {
        if (event.getRegistryKey().equals(BuiltInRegistries.ITEM.key())) {
            GunItemManager.registerGunItem(ModernKineticGunItem.TYPE_NAME, MODERN_KINETIC_GUN);
            GunItemManager.registerGunItem("aa12", AA12); GunItemManager.registerGunItem("ai_awp", AI_AWP);
            GunItemManager.registerGunItem("ak47", AK47); GunItemManager.registerGunItem("aug", AUG);
            GunItemManager.registerGunItem("b93r", B93R); GunItemManager.registerGunItem("cz75", CZ75);
            GunItemManager.registerGunItem("db_long", DB_LONG); GunItemManager.registerGunItem("db_short", DB_SHORT);
            GunItemManager.registerGunItem("deagle", DEAGLE); GunItemManager.registerGunItem("deagle_golden", DEAGLE_GOLDEN);
            GunItemManager.registerGunItem("fn_evolys", FN_EVOLYS); GunItemManager.registerGunItem("fn_fal", FN_FAL);
            GunItemManager.registerGunItem("g36k", G36K); GunItemManager.registerGunItem("glock_17", GLOCK_17);
            GunItemManager.registerGunItem("hk416d", HK416D); GunItemManager.registerGunItem("hk_g3", HK_G3);
            GunItemManager.registerGunItem("hk_mp5a5", HK_MP5A5); GunItemManager.registerGunItem("m1014", M1014);
            GunItemManager.registerGunItem("m107", M107); GunItemManager.registerGunItem("m16a1", M16A1);
            GunItemManager.registerGunItem("m16a4", M16A4); GunItemManager.registerGunItem("m1911", M1911);
            GunItemManager.registerGunItem("m249", M249); GunItemManager.registerGunItem("m320", M320);
            GunItemManager.registerGunItem("m4a1", M4A1); GunItemManager.registerGunItem("m700", M700);
            GunItemManager.registerGunItem("m870", M870); GunItemManager.registerGunItem("m95", M95);
            GunItemManager.registerGunItem("minigun", MINIGUN); GunItemManager.registerGunItem("mk14", MK14);
            GunItemManager.registerGunItem("p320", P320); GunItemManager.registerGunItem("p90", P90);
            GunItemManager.registerGunItem("qbz_191", QBZ_191); GunItemManager.registerGunItem("qbz_95", QBZ_95);
            GunItemManager.registerGunItem("rpg7", RPG7); GunItemManager.registerGunItem("rpk", RPK);
            GunItemManager.registerGunItem("scar_h", SCAR_H); GunItemManager.registerGunItem("scar_l", SCAR_L);
            GunItemManager.registerGunItem("sks_tactical", SKS_TACTICAL); GunItemManager.registerGunItem("spas_12", SPAS_12);
            GunItemManager.registerGunItem("spr15hb", SPR15HB); GunItemManager.registerGunItem("springfield1873", SPRINGFIELD1873);
            GunItemManager.registerGunItem("timeless50", TIMELESS50); GunItemManager.registerGunItem("type_81", TYPE_81);
            GunItemManager.registerGunItem("ump45", UMP45); GunItemManager.registerGunItem("uzi", UZI);
            GunItemManager.registerGunItem("vector45", VECTOR45);
        }
    }

}
