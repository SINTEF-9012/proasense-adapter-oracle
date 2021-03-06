	CREATE TABLE MASCHINEN (
		"MASCH_NR" NVARCHAR2(20) NOT NULL ENABLE,
		"MASCH_ART" NVARCHAR2(1),
		"MGRUPPE" NVARCHAR2(20),
		"BEZEICHNUNG" NVARCHAR2(8),
		"GUT" NVARCHAR2(1),
		"GUT_IMPULS" NVARCHAR2(1),
		"GUT_ZAEHL" NUMBER(37,0),
		"GUT_RS232" NVARCHAR2(1),
		"GUT_PROT" NUMBER(37,0),
		"GUT_MANU" NVARCHAR2(1),
		"GUT_DIM" NVARCHAR2(3),
		"AUS" NVARCHAR2(1),
		"AUS_IMPULS" NVARCHAR2(1),
		"AUS_ZAEHL" NUMBER(37,0),
		"AUS_RS232" NVARCHAR2(1),
		"AUS_PROT" NUMBER(37,0),
		"AUS_MANU" NVARCHAR2(1),
		"AUS_DIM" NVARCHAR2(3),
		"WACHE_TAKT" NVARCHAR2(1),
		"WACHE_ZYKL" NUMBER(37,0),
		"WACHE_SIGN" NVARCHAR2(1),
		"WACHEDAUER" NUMBER(37,0),
		"AUFTR_ERFASS" NVARCHAR2(1),
		"PERS_ERFASS" NVARCHAR2(1),
		"AKKORD" NVARCHAR2(1),
		"KONF_MASKE" NUMBER(37,0),
		"DIGOUT1" NUMBER(37,0),
		"DIGOUT2" NUMBER(37,0),
		"DIGOUT3" NUMBER(37,0),
		"DIGOUT4" NUMBER(37,0),
		"AG01" NVARCHAR2(4),
		"AG02" NVARCHAR2(4),
		"AG03" NVARCHAR2(4),
		"AG04" NVARCHAR2(4),
		"KOSTENSTELLE" NVARCHAR2(10),
		"WARTEZEIT" NUMBER(*,0),
		"LEISTGRAD" NUMBER(37,0),
		"SCHICHTMOD" NUMBER(37,0),
		"MSS" FLOAT(125),
		"PSS" FLOAT(125),
		"BEZ_LANG" NVARCHAR2(40),
		"KFG_KZ01" NVARCHAR2(5),
		"KFG_KZ02" NVARCHAR2(5),
		"KFG_KZ03" NVARCHAR2(5),
		"KFG_KZ04" NVARCHAR2(5),
		"KFG_KZ05" NVARCHAR2(5),
		"ZIELORT" NVARCHAR2(12),
		"PUFFER" NVARCHAR2(12),
		"WERK" NVARCHAR2(10),
		"LINIE" NVARCHAR2(10),
		"VORANMELDEZEIT" NUMBER(*,0),
		"PSHORT_01" NUMBER(37,0),
		"PSHORT_02" NUMBER(37,0),
		"PSHORT_03" NUMBER(37,0),
		"PSHORT_04" NUMBER(37,0),
		"PLONG_01" NUMBER(*,0),
		"PLONG_02" NUMBER(*,0),
		"PLONG_03" NUMBER(*,0),
		"PLONG_04" NUMBER(*,0),
		"PDOUBLE_01" NUMBER(18,6),
		"PDOUBLE_02" NUMBER(18,6),
		"PDOUBLE_03" NUMBER(18,6),
		"PDOUBLE_04" NUMBER(18,6),
		"PARAM_STR01" NVARCHAR2(10),
		"PARAM_STR02" NVARCHAR2(10),
		"PARAM_STR03" NVARCHAR2(30),
		"MAXPERSONEN" NUMBER(*,0),
		"VGW01" NVARCHAR2(20),
		"VGS01" NUMBER(*,0),
		"VGW02" NVARCHAR2(20),
		"VGS02" NUMBER(*,0),
		"VGW03" NVARCHAR2(20),
		"VGS03" NUMBER(*,0),
		"VGW04" NVARCHAR2(20),
		"VGS04" NUMBER(*,0),
		"VGW05" NVARCHAR2(20),
		"VGS05" NUMBER(*,0),
		"VGW06" NVARCHAR2(20),
		"VGS06" NUMBER(*,0),
		"S_SUBGROUP" NVARCHAR2(3),
		"S_VALID_START" DATE,
		"S_VALID_END" DATE,
		"S_CO_AREA" NVARCHAR2(4),
		"S_ACTI1_UNIT" NVARCHAR2(3),
		"S_ACTI1_UNISO" NVARCHAR2(3),
		"S_NOACTI1" NVARCHAR2(1),
		"S_ACTI2_UNIT" NVARCHAR2(3),
		"S_ACTI2_UNISO" NVARCHAR2(3),
		"S_NOACTI2" NVARCHAR2(1),
		"S_ACTI3_UNIT" NVARCHAR2(3),
		"S_ACTI3_UNISO" NVARCHAR2(3),
		"S_NOACTI3" NVARCHAR2(1),
		"S_ACTI4_UNIT" NVARCHAR2(3),
		"S_ACTI4_UNISO" NVARCHAR2(3),
		"S_NOACTI4" NVARCHAR2(1),
		"S_ACTI5_UNIT" NVARCHAR2(3),
		"S_ACTI5_UNISO" NVARCHAR2(3),
		"S_NOACTI5" NVARCHAR2(1),
		"S_ACTI6_UNIT" NVARCHAR2(3),
		"S_ACTI6_UNISO" NVARCHAR2(3),
		"S_NOACTI6" NVARCHAR2(1),
		"S_CO_BUSPROC" NVARCHAR2(12),
		"S_CO_BUSPROC_NAME" NVARCHAR2(20),
		"S_COST_DRIVER" NVARCHAR2(3),
		"S_COST_DRIVER_ISO" NVARCHAR2(3),
		"AUS_NR_IMPULS" NUMBER(37,0),
		"AUS_NR_PSPERRE" NUMBER(37,0),
		"S_TEIL" NUMBER(18,6),
		"S_DIV" NUMBER(18,6),
		"VL_ANZAHL" NUMBER(37,0),
		"ORTGR" NVARCHAR2(4),
		"FIRMA" NVARCHAR2(4),
		"FREMD_APZ" NVARCHAR2(1),
		"AUTO_EINLASTUNG" NVARCHAR2(1),
		"VORGABELISTE" NVARCHAR2(1),
		"PERS_ABMELD" NVARCHAR2(1),
		"A_AN_SK_BEGINN" NVARCHAR2(1),
		"PLANUNGSFUNKTION" NVARCHAR2(1),
		"DRUCKER" NVARCHAR2(100),
		"PAL_ANZ_BEARB" NUMBER(37,0),
		"PAL_UMSPANNZEIT" NUMBER(*,0),
		"DIALOGSTEUERUNG" NVARCHAR2(10),
		"SYMBOL" NVARCHAR2(12),
		"AUSW_J_MOD" NUMBER(37,0),
		"PLAN_J_MOD" NUMBER(37,0),
		"KAP_J_MOD" NUMBER(*,0),
		"SOLL_NUTZGRAD" NUMBER(18,6),
		"SOLL_TECHNUTZGRAD" NUMBER(18,6),
		"SOLL_OEE" NUMBER(18,6),
		"SOLL_EFFEKTIVITAET" NUMBER(18,6),
		"SOLL_QUALITAET" NUMBER(18,6),
		"SOLL_PRODUKTIVITAET" NUMBER(18,6),
		"USER_CODE" NVARCHAR2(8),
		"USER_D_01" DATE,
		"USER_D_02" DATE,
		"USER_D_03" DATE,
		"USER_D_04" DATE,
		"USER_D_05" DATE,
		"USER_D_06" DATE,
		"USER_N_07" NUMBER(*,0),
		"USER_N_08" NUMBER(*,0),
		"USER_N_09" NUMBER(*,0),
		"USER_N_10" NUMBER(*,0),
		"USER_N_11" NUMBER(*,0),
		"USER_N_12" NUMBER(*,0),
		"USER_N_13" NUMBER(*,0),
		"USER_N_14" NUMBER(*,0),
		"USER_N_15" NUMBER(*,0),
		"USER_N_16" NUMBER(*,0),
		"USER_N_17" NUMBER(*,0),
		"USER_N_18" NUMBER(*,0),
		"USER_N_19" NUMBER(*,0),
		"USER_N_20" NUMBER(*,0),
		"USER_N_21" NUMBER(*,0),
		"USER_N_22" NUMBER(*,0),
		"USER_F_23" NUMBER(18,6),
		"USER_F_24" NUMBER(18,6),
		"USER_F_25" NUMBER(18,6),
		"USER_F_26" NUMBER(18,6),
		"USER_F_27" NUMBER(18,6),
		"USER_F_28" NUMBER(18,6),
		"USER_C_29" NVARCHAR2(1),
		"USER_C_30" NVARCHAR2(1),
		"USER_C_31" NVARCHAR2(1),
		"USER_C_32" NVARCHAR2(1),
		"USER_C_33" NVARCHAR2(1),
		"USER_C_34" NVARCHAR2(1),
		"USER_C_35" NVARCHAR2(1),
		"USER_C_36" NVARCHAR2(1),
		"USER_C_37" NVARCHAR2(1),
		"USER_C_38" NVARCHAR2(1),
		"USER_C_39" NVARCHAR2(1),
		"USER_C_40" NVARCHAR2(1),
		"USER_C_41" NVARCHAR2(1),
		"USER_C_42" NVARCHAR2(1),
		"USER_C_43" NVARCHAR2(1),
		"USER_C_44" NVARCHAR2(1),
		"USER_C_45" NVARCHAR2(10),
		"USER_C_46" NVARCHAR2(10),
		"USER_C_47" NVARCHAR2(10),
		"USER_C_48" NVARCHAR2(10),
		"USER_C_49" NVARCHAR2(10),
		"USER_C_50" NVARCHAR2(10),
		"USER_C_51" NVARCHAR2(20),
		"USER_C_52" NVARCHAR2(20),
		"USER_C_53" NVARCHAR2(20),
		"USER_C_54" NVARCHAR2(20),
		"USER_C_55" NVARCHAR2(20),
		"USER_C_56" NVARCHAR2(20),
		"USER_C_57" NVARCHAR2(20),
		"USER_C_58" NVARCHAR2(20),
		"USER_C_59" NVARCHAR2(20),
		"USER_C_60" NVARCHAR2(20),
		"USER_C_61" NVARCHAR2(20),
		"USER_C_62" NVARCHAR2(20),
		"USER_C_63" NVARCHAR2(20),
		"USER_C_64" NVARCHAR2(20),
		"USER_C_65" NVARCHAR2(40),
		"USER_C_66" NVARCHAR2(40),
		"BEARB" NVARCHAR2(10),
		"BEARB_DATE" DATE,
		"BEARB_TIME" NUMBER(*,0),
		"VERANTW_BEREICH" NVARCHAR2(15),
		"MEINHEIT_PRI" NVARCHAR2(3),
		"MEINHEIT_SEK" NVARCHAR2(3),
		"MEINHEIT_TER" NVARCHAR2(3),
		"MEINHEIT_BAS" NVARCHAR2(3),
		"UMRECH_PRI_ZAE" NUMBER(*,0),
		"UMRECH_PRI_NEN" NUMBER(*,0),
		"UMRECH_SEK_ZAE" NUMBER(*,0),
		"UMRECH_SEK_NEN" NUMBER(*,0),
		"UMRECH_TER_ZAE" NUMBER(*,0),
		"UMRECH_TER_NEN" NUMBER(*,0),
		"VERRECH_GUT" NVARCHAR2(10),
		"VERRECH_AUS" NVARCHAR2(10),
		"VERRECH_NCH" NVARCHAR2(10),
		"VERRECH_PRB" NVARCHAR2(10),
		"GUT_MANU_TAKTE" NVARCHAR2(1),
		"AUS_MANU_TAKTE" NVARCHAR2(1),
		"NCH_MANU_TAKTE" NVARCHAR2(1),
		"PRB_MANU_TAKTE" NVARCHAR2(1),
		"NCH_MANU" NVARCHAR2(1),
		"PRB_MANU" NVARCHAR2(1),
		"VIS_LIST3" NVARCHAR2(10),
		"OPT_MDETLG" NVARCHAR2(1),
		"AUSWMOD" NUMBER(37,0),
		"VIS_FHM_TNR_A_AN" NVARCHAR2(1)
			)
			SEGMENT CREATION IMMEDIATE
			PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
			STORAGE(INITIAL 32768 NEXT 32768 MINEXTENTS 1 MAXEXTENTS 2147483645
			PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT);

	CREATE TABLE AUFTRAGS_BESTAND(
		"AUFTRAG_NR" NVARCHAR2(40),
		"AUFTRAG_ART" NVARCHAR2(5),
		"A_STUFE" NVARCHAR2(3),
		"MEHRFACH_KZ" NVARCHAR2(1),
		"WPKZ" NVARCHAR2(1),
		"KUNDEN_BEZ" NVARCHAR2(40),
		"MASCH_NR" NVARCHAR2(20),
		"SOLL_TEIL" NUMBER(18,6),
		"SOLL_DAUER" NUMBER(18,6),
		"ERFASS_ART" NVARCHAR2(1),
		"ARTIKEL" NVARCHAR2(40),
		"ARBPLAN" NVARCHAR2(40),
		"RUEST_ZEIT" NUMBER(*,0),
		"LIEGE_ZEIT" NUMBER(*,0),
		"ABRUEST_ZEIT" NUMBER(*,0),
		"MB_VERHAELTNIS" FLOAT(125),
		"KOPF_VERWEIS" NUMBER(*,0),
		"FRUEH_ANF_DAT" DATE,
		"FRUEH_ANF_ZEIT" NUMBER(*,0),
		"SPAET_END_DAT" DATE,
		"SPAET_END_ZEIT" NUMBER(*,0),
		"ERRANF_DAT" DATE,
		"ERRANF_ZEIT" NUMBER(*,0),
		"ERREND_DAT" DATE,
		"ERREND_ZEIT" NUMBER(*,0),
		"AFO_LFD_NR" NUMBER(*,0),
		"LAST_AFO" NVARCHAR2(1),
		"A_PRIO" NVARCHAR2(4),
		"AG_BEZ" NVARCHAR2(40),
		"SPLITTEN" NVARCHAR2(1),
		"SOLL_TE" NUMBER(18,6),
		"SOLL_TR" NUMBER(18,6),
		"LOHNART" NVARCHAR2(4),
		"LIEGE_ZEIT_MAX" NUMBER(*,0),
		"ANZ_SPLIT_MASCH" NUMBER(37,0),
		"LS_SPLIT_KZ" NVARCHAR2(1),
		"LS_SAMMEL_KZ" NVARCHAR2(1),
		"LS_SAFO_NR" NVARCHAR2(40),
		"LS_SPLIT_ANZ" NUMBER(37,0),
		"CHARGEN_PFLICHT" NVARCHAR2(1),
		"MB_VERH_RUESTEN" FLOAT(125),
		"MATETYP" NVARCHAR2(20),
		"FARBE" NVARCHAR2(20),
		"SAP_AUFTRAG_NR" NVARCHAR2(12),
		"SAP_VGFOLGE" NVARCHAR2(6),
		"SAP_VG_NR" NVARCHAR2(4),
		"SAP_UVG" NVARCHAR2(4),
		"SAP_SPLIT_NR" NUMBER(37,0),
		"SAP_MRFLG" NVARCHAR2(1),
		"PLAN_WERK" NVARCHAR2(4),
		"SAP_MAUFNR" NVARCHAR2(12),
		"SAP_MAPLFL" NVARCHAR2(6),
		"SAP_MVORNR" NVARCHAR2(4),
		"SAP_LAUFNR" NVARCHAR2(12),
		"SAP_BEZFL" NVARCHAR2(6),
		"SAP_VORNR1" NVARCHAR2(4),
		"SAP_VORNR2" NVARCHAR2(4),
		"TRANSFER_DATUM" DATE,
		"SOLL_TEB" NUMBER(18,6),
		"ANMELDOPT" NVARCHAR2(10),
		"ABMELDOPT" NVARCHAR2(10),
		"SOLLMENGENFORTS" NVARCHAR2(1),
		"S_SOURCE_SYS" NVARCHAR2(10),
		"S_HDR_NOMINATOR" NUMBER(*,0),
		"S_HDR_DENOMINATOR" NUMBER(*,0),
		"S_CO_BPC_1" NVARCHAR2(12),
		"S_BPC_UNIT_1" NVARCHAR2(3),
		"S_BPC_UNISO_1" NVARCHAR2(3),
		"S_BPC_QUAN_1" NUMBER(18,6),
		"ARTIKEL_BEZ" NVARCHAR2(40),
		"RF_M_AUFTRAG_NR" NVARCHAR2(40),
		"RF_ROSCH" NVARCHAR2(1),
		"RF_OPCODE" NVARCHAR2(10),
		"RF_E_BREITE" NUMBER(18,6),
		"RF_A_BREITE" NUMBER(18,6),
		"RF_AGVFA" NUMBER(18,6),
		"RF_ROANZ" NUMBER(*,0),
		"RF_STFLAECH" NUMBER(18,6),
		"RF_BSBRS" NUMBER(18,6),
		"RF_ZUZWEIGMENGE" NUMBER(*,0),
		"RF_AGTYP" NVARCHAR2(1),
		"RF_ABZWEIG" NVARCHAR2(1),
		"RF_ZUZWEIG" NVARCHAR2(1),
		"RF_HUEGEWICHT" NUMBER(18,6),
		"RF_ETR_ANZ" NUMBER(*,0),
		"RF_GTR_ANZ" NUMBER(*,0),
		"PRPLAN_NR" NUMBER(*,0),
		"VERWEIS" NUMBER(36,0) NOT NULL ENABLE,
		"A_TYP" NVARCHAR2(4),
		"AUNR" NVARCHAR2(40),
		"AFOLG" NVARCHAR2(10),
		"AGNR" NVARCHAR2(20),
		"UAGNR" NVARCHAR2(20),
		"SPLNR" NVARCHAR2(10),
		"RMNR" NVARCHAR2(40),
		"AG_ART" NVARCHAR2(5),
		"PRUEF_MASCH_NR" NVARCHAR2(20),
		"OP_CODE" NVARCHAR2(10),
		"MEINHEIT_BAS" NVARCHAR2(3),
		"MEINHEIT_PRI" NVARCHAR2(3),
		"MEINHEIT_SEK" NVARCHAR2(3),
		"MEINHEIT_TER" NVARCHAR2(3),
		"MEINHEIT_LIEF" NVARCHAR2(3),
		"SOLL_MENGE_BAS" NUMBER(18,6),
		"SOLL_MENGE_PRI" NUMBER(18,6),
		"SOLL_MENGE_SEK" NUMBER(18,6),
		"SOLL_MENGE_TER" NUMBER(18,6),
		"SOLL_AUS_BAS" NUMBER(18,6),
		"SOLL_AUS_PRI" NUMBER(18,6),
		"SOLL_AUS_SEK" NUMBER(18,6),
		"SOLL_AUS_TER" NUMBER(18,6),
		"UMRECH_LIEF_ZAE" NUMBER(*,0),
		"UMRECH_LIEF_NEN" NUMBER(*,0),
		"UMRECH_PRI_ZAE" NUMBER(*,0),
		"UMRECH_PRI_NEN" NUMBER(*,0),
		"UMRECH_SEK_ZAE" NUMBER(*,0),
		"UMRECH_SEK_NEN" NUMBER(*,0),
		"UMRECH_TER_ZAE" NUMBER(*,0),
		"UMRECH_TER_NEN" NUMBER(*,0),
		"SOLL_DIV" NUMBER(18,6),
		"EINH_MENGE" NUMBER(18,6),
		"EINH_MENGE_EINH" NVARCHAR2(3),
		"SOLL_DAUER_FMT" NVARCHAR2(3),
		"SOLL_DAUER_EXPR" NVARCHAR2(6),
		"SOLL_DAUER_MOD" NVARCHAR2(1),
		"VORLAUFZEIT" NUMBER(*,0),
		"WEITERGABEMENGE" NUMBER(18,6),
		"RLZ_EXPR" NVARCHAR2(6),
		"RLZ2_EXPR" NVARCHAR2(6),
		"RES_WNR" NVARCHAR2(40),
		"RES_DNC" NVARCHAR2(40),
		"RES_EMAT" NVARCHAR2(40),
		"FREMDBEARBEITUNG" NVARCHAR2(1),
		"WARTE_ZEIT_NORM" NUMBER(*,0),
		"WARTE_ZEIT" NUMBER(*,0),
		"WARTE_ZEIT_MIN" NUMBER(*,0),
		"WARTE_ZEIT_MOD" NVARCHAR2(1),
		"WARTE_ZEIT_EXPR" NVARCHAR2(6),
		"RUEST_ZEIT_MOD" NVARCHAR2(1),
		"RUEST_ZEIT_EXPR" NVARCHAR2(6),
		"RUEST_ZEIT_ZUSCHL" NUMBER(*,0),
		"BEARB_ZEIT" NUMBER(*,0),
		"BEARB_ZEIT_MOD" NVARCHAR2(1),
		"BEARB_ZEIT_EXPR" NVARCHAR2(6),
		"PRUEF_ZEIT" NUMBER(*,0),
		"PRUEF_ZEIT_MOD" NVARCHAR2(1),
		"PRUEF_ZEIT_EXPR" NVARCHAR2(6),
		"ABRUEST_ZEIT_MOD" NVARCHAR2(1),
		"ABRUEST_ZEIT_EXPR" NVARCHAR2(6),
		"TRANSP_ZEIT_NORM" NUMBER(*,0),
		"TRANSP_ZEIT" NUMBER(*,0),
		"TRANSP_ZEIT_MIN" NUMBER(*,0),
		"TRANSP_ZEIT_KAL" NUMBER(*,0),
		"LIEFERZEIT" NUMBER(*,0),
		"LST_CODE" NVARCHAR2(6),
		"MGRUPPE" NVARCHAR2(20),
		"SPAET_ANF_DAT" DATE,
		"SPAET_ANF_ZEIT" NUMBER(*,0),
		"FRUEH_END_DAT" DATE,
		"FRUEH_END_ZEIT" NUMBER(*,0),
		"TERM_ANF_DAT" DATE,
		"TERM_ANF_ZEIT" NUMBER(*,0),
		"TERM_END_DAT" DATE,
		"TERM_END_ZEIT" NUMBER(*,0),
		"SORT_DAT" DATE,
		"SORT_ZEIT" NUMBER(*,0),
		"SOLL_TE_FMT" NVARCHAR2(3),
		"SOLL_TE_EXPR" NVARCHAR2(6),
		"SOLL_TE_MOD" NVARCHAR2(1),
		"SOLL_TR_EXPR" NVARCHAR2(6),
		"SOLL_TR_MOD" NVARCHAR2(1),
		"SOLL_TEB_FMT" NVARCHAR2(3),
		"SOLL_TEB_EXPR" NVARCHAR2(6),
		"SOLL_TEB_MOD" NVARCHAR2(1),
		"SOLL_TRB_EXPR" NVARCHAR2(6),
		"SOLL_TRB_MOD" NVARCHAR2(1),
		"SOLL_TRB_FMT" NVARCHAR2(3),
		"SOLL_TRB" NUMBER(18,6),
		"SERIALNR_PFLICHT" NVARCHAR2(1),
		"ALS_DSBEZ" NVARCHAR2(20),
		"MENGE_PRI_UNTLI_R" NVARCHAR2(1),
		"MENGE_PRI_UEBLI_R" NVARCHAR2(1),
		"MENGE_PRI_UNTLI" NUMBER(18,6),
		"MENGE_PRI_UEBLI" NUMBER(18,6),
		"VERANTW_BEREICH" NVARCHAR2(15),
		"SAP_USR00" NVARCHAR2(20),
		"SAP_USR01" NVARCHAR2(20),
		"SAP_USR04" NUMBER(18,6),
		"SAP_USR04_UNIT" NVARCHAR2(3),
		"TRANSFER_ZEIT" NUMBER(*,0),
		"TRANSFER" NVARCHAR2(10),
		"PDV_PRUEFPLAN_NR" NVARCHAR2(20),
		"FIRMA" NVARCHAR2(4),
		"KOSTENSTELLE" NVARCHAR2(10),
		"KOSTENART" NVARCHAR2(20),
		"VGW_CODE" NVARCHAR2(5),
		"VGW_01" NUMBER(18,6),
		"VGW_02" NUMBER(18,6),
		"VGW_03" NUMBER(18,6),
		"VGW_04" NUMBER(18,6),
		"VGW_05" NUMBER(18,6),
		"VGW_06" NUMBER(18,6),
		"VGW_07" NUMBER(18,6),
		"VGW_08" NUMBER(18,6),
		"VGW_09" NUMBER(18,6),
		"VGW_10" NUMBER(18,6),
		"VERARB_CODE" NVARCHAR2(6),
		"VERARB_CODE_MOD" NUMBER(*,0),
		"VORGABELISTE" NVARCHAR2(1),
		"ERFASSBAR" NVARCHAR2(1),
		"ANLAGE" NVARCHAR2(10),
		"ANLAGE_DATE" DATE,
		"ANLAGE_TIME" NUMBER(*,0),
		"BEARB" NVARCHAR2(10),
		"BEARB_DATE" DATE,
		"BEARB_TIME" NUMBER(*,0),
		"GEAENDERT_PPS" NVARCHAR2(1),
		"GEAENDERT_HYDRA" NVARCHAR2(1),
		"PUFFER_ZEIT" NUMBER(*,0),
		"RED_PUFFER_ZEIT" NUMBER(*,0),
		"TERMINIEREN" NVARCHAR2(1),
		"FERTVAR_VERWEIS" NUMBER(*,0),
		"AG_VERWEIS" NUMBER(*,0),
		"USER_CODE" NVARCHAR2(8),
		"USER_D_01" DATE,
		"USER_D_02" DATE,
		"USER_D_03" DATE,
		"USER_D_04" DATE,
		"USER_D_05" DATE,
		"USER_D_06" DATE,
		"USER_N_07" NUMBER(*,0),
		"USER_N_08" NUMBER(*,0),
		"USER_N_09" NUMBER(*,0),
		"USER_N_10" NUMBER(*,0),
		"USER_N_11" NUMBER(*,0),
		"USER_N_12" NUMBER(*,0),
		"USER_N_13" NUMBER(*,0),
		"USER_N_14" NUMBER(*,0),
		"USER_N_15" NUMBER(*,0),
		"USER_N_16" NUMBER(*,0),
		"USER_N_17" NUMBER(*,0),
		"USER_N_18" NUMBER(*,0),
		"USER_N_19" NUMBER(*,0),
		"USER_N_20" NUMBER(*,0),
		"USER_N_21" NUMBER(*,0),
		"USER_N_22" NUMBER(*,0),
		"USER_F_23" NUMBER(18,6),
		"USER_F_24" NUMBER(18,6),
		"USER_F_25" NUMBER(18,6),
		"USER_F_26" NUMBER(18,6),
		"USER_F_27" NUMBER(18,6),
		"USER_F_28" NUMBER(18,6),
		"USER_C_29" NVARCHAR2(1),
		"USER_C_30" NVARCHAR2(1),
		"USER_C_31" NVARCHAR2(1),
		"USER_C_32" NVARCHAR2(1),
		"USER_C_33" NVARCHAR2(1),
		"USER_C_34" NVARCHAR2(1),
		"USER_C_35" NVARCHAR2(1),
		"USER_C_36" NVARCHAR2(1),
		"USER_C_37" NVARCHAR2(1),
		"USER_C_38" NVARCHAR2(1),
		"USER_C_39" NVARCHAR2(1),
		"USER_C_40" NVARCHAR2(1),
		"USER_C_41" NVARCHAR2(1),
		"USER_C_42" NVARCHAR2(1),
		"USER_C_43" NVARCHAR2(1),
		"USER_C_44" NVARCHAR2(1),
		"USER_C_45" NVARCHAR2(10),
		"USER_C_46" NVARCHAR2(10),
		"USER_C_47" NVARCHAR2(10),
		"USER_C_48" NVARCHAR2(10),
		"USER_C_49" NVARCHAR2(10),
		"USER_C_50" NVARCHAR2(10),
		"USER_C_51" NVARCHAR2(20),
		"USER_C_52" NVARCHAR2(20),
		"USER_C_53" NVARCHAR2(20),
		"USER_C_54" NVARCHAR2(20),
		"USER_C_55" NVARCHAR2(20),
		"USER_C_56" NVARCHAR2(20),
		"USER_C_57" NVARCHAR2(20),
		"USER_C_58" NVARCHAR2(20),
		"USER_C_59" NVARCHAR2(20),
		"USER_C_60" NVARCHAR2(20),
		"USER_C_61" NVARCHAR2(20),
		"USER_C_62" NVARCHAR2(20),
		"USER_C_63" NVARCHAR2(20),
		"USER_C_64" NVARCHAR2(20),
		"USER_C_65" NVARCHAR2(40),
		"USER_C_66" NVARCHAR2(40),
		"TEXT_VORH" NVARCHAR2(1),
		"NOTIZ_VORH" NVARCHAR2(1),
		"LAST_TERM_DATE" DATE,
		"LAST_TERM_TIME" NUMBER(*,0),
		"GEAENDERT_PLANUNG" NVARCHAR2(1),
		"LAGERORT" NVARCHAR2(12),
		"SOLL_BEDIEN_ANT" NUMBER(18,6),
		"VERBRAUCH_ART" NVARCHAR2(2),
		"LAYOUT" NVARCHAR2(20),
		"SZY_ABW_PROZ" NUMBER(8,3),
		"AG_VERKN_TYP" NVARCHAR2(1),
		"QM_M_AUFTRAG_NR" NVARCHAR2(40),
		"QM_AG_TYP" NVARCHAR2(2),
		"QUALIFIKATION" NUMBER(*,0),
		"QUAL_RUESTEN" NUMBER(*,0),
		"SOLL_MENGE_ANSATZ" NUMBER(18,6)
			)
			SEGMENT CREATION IMMEDIATE
			PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
			STORAGE(INITIAL 1048576 NEXT 2097152 MINEXTENTS 1 MAXEXTENTS 2147483645
			PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT);

	CREATE TABLE AUFTRAG_STATUS (
		"AUFTRAG_NR" NVARCHAR2(40),
		"A_STATUS" NVARCHAR2(5),
		"PROT_ZEIT" NUMBER(*,0),
		"PROT_DAT" DATE,
		"ABBR_GRUND" NUMBER(*,0),
		"PERSON_ANZ" NUMBER(*,0),
		"ANMELDZEIT" NUMBER(*,0),
		"ANMELDDAT" DATE,
		"DAUER" NUMBER(*,0),
		"TERMINALNR" NUMBER(37,0),
		"MASCH_NR" NVARCHAR2(20),
		"BMK_01" NUMBER(*,0),
		"BMK_02" NUMBER(*,0),
		"BMK_03" NUMBER(*,0),
		"BMK_04" NUMBER(*,0),
		"BMK_05" NUMBER(*,0),
		"BMK_06" NUMBER(*,0),
		"BMK_07" NUMBER(*,0),
		"BMK_08" NUMBER(*,0),
		"BMK_09" NUMBER(*,0),
		"BMK_10" NUMBER(*,0),
		"BMK_11" NUMBER(*,0),
		"BMK_12" NUMBER(*,0),
		"PERS_DAUER" NUMBER(*,0),
		"A_STATUS_VORG" NVARCHAR2(5),
		"SAP_STATV" NVARCHAR2(5),
		"SAP_STATA" NVARCHAR2(1),
		"SAP_STATE" NVARCHAR2(1),
		"IST_WERK" NVARCHAR2(4),
		"IST_LST_01" NUMBER(18,6),
		"IST_LST_02" NUMBER(18,6),
		"IST_LST_03" NUMBER(18,6),
		"IST_LST_04" NUMBER(18,6),
		"IST_LST_05" NUMBER(18,6),
		"IST_LST_06" NUMBER(18,6),
		"RUEST_ZYKLEN" NUMBER(*,0),
		"BEARB_ZYKLEN" NUMBER(*,0),
		"ABRUEST_ZYKLEN" NUMBER(*,0),
		"VL_ZYKLEN" NUMBER(*,0),
		"S_CONF_YIELD" NUMBER(18,6),
		"S_CONF_SCRAP" NUMBER(18,6),
		"S_CONF_REWORK" NUMBER(18,6),
		"S_CONF_BPC_1" NUMBER(18,6),
		"S_NO_REMN_BPC_1" NVARCHAR2(1),
		"SAP_PERSBEZUG" NVARCHAR2(1),
		"A_TYP" NVARCHAR2(4),
		"PROD_KENN" NVARCHAR2(2),
		"SEK_STATUS" NVARCHAR2(5),
		"A_STATUSTEXT_NR" NUMBER(*,0),
		"SEK_STATUSTEXT_NR" NUMBER(*,0),
		"FIX" NVARCHAR2(1),
		"EINGEPLANT" NVARCHAR2(1),
		"RES1_STATUS" NVARCHAR2(2),
		"RES2_STATUS" NVARCHAR2(2),
		"RES3_STATUS" NVARCHAR2(2),
		"RES4_STATUS" NVARCHAR2(2),
		"RES5_STATUS" NVARCHAR2(2),
		"RES6_STATUS" NVARCHAR2(2),
		"VWE_ID" NVARCHAR2(20),
		"VWE_CODE" NVARCHAR2(10),
		"AP_GEDRUCKT" NVARCHAR2(10),
		"PROD_KENN_VORG" NVARCHAR2(2),
		"E_ANMELD_DAT" DATE,
		"E_ANMELD_ZEIT" NUMBER(*,0),
		"L_ABMELD_DAT" DATE,
		"L_ABMELD_ZEIT" NUMBER(*,0),
		"U_ABMELD_DAT" DATE,
		"U_ABMELD_ZEIT" NUMBER(*,0),
		"PROG_ABMELD_DAT" DATE,
		"PROG_ABMELD_ZEIT" NUMBER(*,0),
		"GUT_BAS" NUMBER(18,6),
		"GUT_PRI" NUMBER(18,6),
		"GUT_SEK" NUMBER(18,6),
		"GUT_TER" NUMBER(18,6),
		"AUS_BAS" NUMBER(18,6),
		"AUS_PRI" NUMBER(18,6),
		"AUS_SEK" NUMBER(18,6),
		"AUS_TER" NUMBER(18,6),
		"NACHARB_BAS" NUMBER(18,6),
		"NACHARB_PRI" NUMBER(18,6),
		"NACHARB_SEK" NUMBER(18,6),
		"NACHARB_TER" NUMBER(18,6),
		"PROBLEM_BAS" NUMBER(18,6),
		"PROBLEM_PRI" NUMBER(18,6),
		"PROBLEM_SEK" NUMBER(18,6),
		"PROBLEM_TER" NUMBER(18,6),
		"MEINHEIT_BAS" NVARCHAR2(3),
		"MEINHEIT_PRI" NVARCHAR2(3),
		"MEINHEIT_SEK" NVARCHAR2(3),
		"MEINHEIT_TER" NVARCHAR2(3),
		"HUB_GESAMT" NUMBER(18,6),
		"HUB_GUT" NUMBER(18,6),
		"IST_ZYKLUS" NUMBER(*,0),
		"GESPERRT" NVARCHAR2(1),
		"SPERR" NVARCHAR2(10),
		"SPERR_DATE" DATE,
		"SPERR_TIME" NUMBER(*,0),
		"ENTSPERR" NVARCHAR2(10),
		"ENTSPERR_DATE" DATE,
		"ENTSPERR_TIME" NUMBER(*,0),
		"REAK" NVARCHAR2(10),
		"REAK_DATE" DATE,
		"REAK_TIME" NUMBER(*,0),
		"GESPERRT_PFLEGE" NVARCHAR2(1),
		"SPERR_PFLEGE" NVARCHAR2(10),
		"SPERR_PFLEGE_DATE" DATE,
		"SPERR_PFLEGE_TIME" NUMBER(*,0),
		"USER_CODE" NVARCHAR2(8),
		"USER_D_01" DATE,
		"USER_D_02" DATE,
		"USER_D_03" DATE,
		"USER_D_04" DATE,
		"USER_D_05" DATE,
		"USER_D_06" DATE,
		"USER_N_07" NUMBER(*,0),
		"USER_N_08" NUMBER(*,0),
		"USER_N_09" NUMBER(*,0),
		"USER_N_10" NUMBER(*,0),
		"USER_N_11" NUMBER(*,0),
		"USER_N_12" NUMBER(*,0),
		"USER_N_13" NUMBER(*,0),
		"USER_N_14" NUMBER(*,0),
		"USER_N_15" NUMBER(*,0),
		"USER_N_16" NUMBER(*,0),
		"USER_N_17" NUMBER(*,0),
		"USER_N_18" NUMBER(*,0),
		"USER_N_19" NUMBER(*,0),
		"USER_N_20" NUMBER(*,0),
		"USER_N_21" NUMBER(*,0),
		"USER_N_22" NUMBER(*,0),
		"USER_F_23" NUMBER(18,6),
		"USER_F_24" NUMBER(18,6),
		"USER_F_25" NUMBER(18,6),
		"USER_F_26" NUMBER(18,6),
		"USER_F_27" NUMBER(18,6),
		"USER_F_28" NUMBER(18,6),
		"USER_C_29" NVARCHAR2(1),
		"USER_C_30" NVARCHAR2(1),
		"USER_C_31" NVARCHAR2(1),
		"USER_C_32" NVARCHAR2(1),
		"USER_C_33" NVARCHAR2(1),
		"USER_C_34" NVARCHAR2(1),
		"USER_C_35" NVARCHAR2(1),
		"USER_C_36" NVARCHAR2(1),
		"USER_C_37" NVARCHAR2(1),
		"USER_C_38" NVARCHAR2(1),
		"USER_C_39" NVARCHAR2(1),
		"USER_C_40" NVARCHAR2(1),
		"USER_C_41" NVARCHAR2(1),
		"USER_C_42" NVARCHAR2(1),
		"USER_C_43" NVARCHAR2(1),
		"USER_C_44" NVARCHAR2(1),
		"USER_C_45" NVARCHAR2(10),
		"USER_C_46" NVARCHAR2(10),
		"USER_C_47" NVARCHAR2(10),
		"USER_C_48" NVARCHAR2(10),
		"USER_C_49" NVARCHAR2(10),
		"USER_C_50" NVARCHAR2(10),
		"USER_C_51" NVARCHAR2(20),
		"USER_C_52" NVARCHAR2(20),
		"USER_C_53" NVARCHAR2(20),
		"USER_C_54" NVARCHAR2(20),
		"USER_C_55" NVARCHAR2(20),
		"USER_C_56" NVARCHAR2(20),
		"USER_C_57" NVARCHAR2(20),
		"USER_C_58" NVARCHAR2(20),
		"USER_C_59" NVARCHAR2(20),
		"USER_C_60" NVARCHAR2(20),
		"USER_C_61" NVARCHAR2(20),
		"USER_C_62" NVARCHAR2(20),
		"USER_C_63" NVARCHAR2(20),
		"USER_C_64" NVARCHAR2(20),
		"USER_C_65" NVARCHAR2(40),
		"USER_C_66" NVARCHAR2(40),
		"P_DAUER_BMK01" NUMBER(*,0),
		"P_DAUER_BMK02" NUMBER(*,0),
		"P_DAUER_BMK03" NUMBER(*,0),
		"P_DAUER_BMK04" NUMBER(*,0),
		"P_DAUER_BMK05" NUMBER(*,0),
		"P_DAUER_BMK06" NUMBER(*,0),
		"P_DAUER_BMK07" NUMBER(*,0),
		"P_DAUER_BMK08" NUMBER(*,0),
		"P_DAUER_BMK09" NUMBER(*,0),
		"P_DAUER_BMK10" NUMBER(*,0),
		"P_DAUER_BMK11" NUMBER(*,0),
		"P_DAUER_BMK12" NUMBER(*,0),
		"REST_P_DAUER_BMK01" NUMBER(*,0),
		"REST_P_DAUER_BMK02" NUMBER(*,0),
		"REST_P_DAUER_BMK03" NUMBER(*,0),
		"REST_P_DAUER_BMK04" NUMBER(*,0),
		"REST_P_DAUER_BMK05" NUMBER(*,0),
		"REST_P_DAUER_BMK06" NUMBER(*,0),
		"REST_P_DAUER_BMK07" NUMBER(*,0),
		"REST_P_DAUER_BMK08" NUMBER(*,0),
		"REST_P_DAUER_BMK09" NUMBER(*,0),
		"REST_P_DAUER_BMK10" NUMBER(*,0),
		"REST_P_DAUER_BMK11" NUMBER(*,0),
		"REST_P_DAUER_BMK12" NUMBER(*,0),
		"REST_P_DAUER" NUMBER(*,0),
		"CALC_BMK_01" NUMBER(*,0),
		"CALC_BMK_02" NUMBER(*,0),
		"CALC_BMK_03" NUMBER(*,0),
		"CALC_BMK_04" NUMBER(*,0),
		"CALC_BMK_05" NUMBER(*,0),
		"CALC_BMK_06" NUMBER(*,0),
		"CALC_BMK_07" NUMBER(*,0),
		"CALC_BMK_08" NUMBER(*,0),
		"CALC_BMK_09" NUMBER(*,0),
		"CALC_BMK_10" NUMBER(*,0),
		"CALC_BMK_11" NUMBER(*,0),
		"CALC_BMK_12" NUMBER(*,0),
		"CALC_DAUER" NUMBER(*,0),
		"CALC_PERS_DAUER" NUMBER(*,0),
		"CALC_P_DAUER_BMK01" NUMBER(*,0),
		"CALC_P_DAUER_BMK02" NUMBER(*,0),
		"CALC_P_DAUER_BMK03" NUMBER(*,0),
		"CALC_P_DAUER_BMK04" NUMBER(*,0),
		"CALC_P_DAUER_BMK05" NUMBER(*,0),
		"CALC_P_DAUER_BMK06" NUMBER(*,0),
		"CALC_P_DAUER_BMK07" NUMBER(*,0),
		"CALC_P_DAUER_BMK08" NUMBER(*,0),
		"CALC_P_DAUER_BMK09" NUMBER(*,0),
		"CALC_P_DAUER_BMK10" NUMBER(*,0),
		"CALC_P_DAUER_BMK11" NUMBER(*,0),
		"CALC_P_DAUER_BMK12" NUMBER(*,0),
		"REST_BMK_01" NUMBER(*,0),
		"REST_BMK_02" NUMBER(*,0),
		"REST_BMK_03" NUMBER(*,0),
		"REST_BMK_04" NUMBER(*,0),
		"REST_BMK_05" NUMBER(*,0),
		"REST_BMK_06" NUMBER(*,0),
		"REST_BMK_07" NUMBER(*,0),
		"REST_BMK_08" NUMBER(*,0),
		"REST_BMK_09" NUMBER(*,0),
		"REST_BMK_10" NUMBER(*,0),
		"REST_BMK_11" NUMBER(*,0),
		"REST_BMK_12" NUMBER(*,0),
		"REST_DAUER" NUMBER(*,0),
		"BEARB" NVARCHAR2(10),
		"BEARB_DATE" DATE,
		"BEARB_TIME" NUMBER(*,0),
		"REST_PERS_DAUER" NUMBER(*,0),
		"IST_LST_07" NUMBER(18,6),
		"IST_LST_08" NUMBER(18,6),
		"IST_LST_09" NUMBER(18,6),
		"IST_LST_10" NUMBER(18,6),
		"REST_LST_01" NUMBER(18,6),
		"REST_LST_02" NUMBER(18,6),
		"REST_LST_03" NUMBER(18,6),
		"REST_LST_04" NUMBER(18,6),
		"REST_LST_05" NUMBER(18,6),
		"REST_LST_06" NUMBER(18,6),
		"REST_LST_07" NUMBER(18,6),
		"REST_LST_08" NUMBER(18,6),
		"REST_LST_09" NUMBER(18,6),
		"REST_LST_10" NUMBER(18,6),
		"ANMELDBAR" NVARCHAR2(1),
		"EBDF" NVARCHAR2(1),
		"CAQ_PROD_KENN" NVARCHAR2(2),
		"CAQ_STATUS" NVARCHAR2(5),
		"CAQ_STATUSTEXT_NR" NUMBER(*,0),
		"CAQ_ERG_PROD_KENN" NVARCHAR2(2),
		"CAQ_ERG_STATUS" NVARCHAR2(5),
		"CAQ_ERG_TEXT_NR" NUMBER(*,0),
		"CHARGEN_NR" NVARCHAR2(20),
		"STA_MENGENANP" NVARCHAR2(1),
		"LOS_IDX" NUMBER(*,0),
		"TRANR_ART" NVARCHAR2(10),
		"TRANR_CNR" NVARCHAR2(20),
		"TRANR_RES" NVARCHAR2(40),
		"TRANR_RESTYP" NVARCHAR2(4),
		"TRANR_SMP" NVARCHAR2(12),
		"TRANR_TMP" NVARCHAR2(12),
		"TRIGGER_ANR" NVARCHAR2(40)
			)
			SEGMENT CREATION IMMEDIATE
			PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
			STORAGE(INITIAL 1048576 NEXT 2097152 MINEXTENTS 1 MAXEXTENTS 2147483645
			PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT);

	CREATE TABLE ADE_GRUND_TEXTE (
		"GRUNDTEXT_NR" NUMBER(*,0) NOT NULL ENABLE,
		"GRUNDTEXT" NVARCHAR2(30),
		"BEARB" NVARCHAR2(10),
		"BEARB_DATE" DATE,
		"BEARB_TIME" NUMBER(*,0)
			)
		SEGMENT CREATION IMMEDIATE
			PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
			STORAGE(INITIAL 131072 NEXT 524288 MINEXTENTS 1 MAXEXTENTS 2147483645
			PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT);

	CREATE TABLE ADE_AUFTRAGMENGEN (
		"A_TYP" NVARCHAR2(4),
		"AUFTRAG_NR" NVARCHAR2(40),
		"MENGEN_ART" NVARCHAR2(1),
		"GRUND" NUMBER(*,0),
		"GRUND_TEXT" NUMBER(*,0),
		"IST_BAS" NUMBER(18,6),
		"IST_PRI" NUMBER(18,6),
		"IST_SEK" NUMBER(18,6),
		"IST_TER" NUMBER(18,6),
		"MEINHEIT_BAS" NVARCHAR2(3),
		"MEINHEIT_PRI" NVARCHAR2(3),
		"MEINHEIT_SEK" NVARCHAR2(3),
		"MEINHEIT_TER" NVARCHAR2(3)
			)
			SEGMENT CREATION IMMEDIATE
			PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 NOCOMPRESS LOGGING
			STORAGE(INITIAL 131072 NEXT 2097152 MINEXTENTS 1 MAXEXTENTS 2147483645
			PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1 BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT);
