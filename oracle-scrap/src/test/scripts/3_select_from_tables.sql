SET linesize 360;
select ANLAGE_DATE as CREATED_DATE, ANLAGE_TIME as CREATED_TIME,
AUFTRAGS_BESTAND.BEARB_DATE, AUFTRAGS_BESTAND.BEARB_TIME,
AUFTRAGS_BESTAND.MASCH_NR as MACHINE_NO,
ADE_AUFTRAGMENGEN.auftrag_nr as ORDER_OPERATION_NO,
IST_PRI as SCRAP_COUNT, GRUNDTEXT as SCRAP_REASON,
ARTIKEL as FINAL_ARTICLE
from AUFTRAGS_BESTAND

join ADE_AUFTRAGMENGEN  on
ADE_AUFTRAGMENGEN.AUFTRAG_NR = AUFTRAGS_BESTAND.AUFTRAG_NR
join ADE_GRUND_TEXTE on
ADE_AUFTRAGMENGEN.GRUND_TEXT = ADE_GRUND_TEXTE.GRUNDTEXT_NR
join AUFTRAG_STATUS on
AUFTRAGS_BESTAND.AUFTRAG_NR = AUFTRAG_STATUS.AUFTRAG_NR

where AUFTRAG_STATUS.A_STATUS = 'E'
and
AUFTRAGS_BESTAND.MASCH_NR IS NOT NULL
and
auftrags_bestand.BEARB_DATE = TO_DATE(TO_CHAR(CURRENT_DATE, 'YYYY-MM-DD'),'YYYY-MM-DD') - 1;