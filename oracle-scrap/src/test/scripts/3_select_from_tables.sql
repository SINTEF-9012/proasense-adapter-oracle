SET linesize 360;
ALTER SESSION SET nls_date_format='DD-MM-YYYY HH24:MI:SS';
-- noinspection SqlDialectInspection
select AUFTRAGS_BESTAND.BEARB_DATE as MEASUREMENT_TIME,
AUFTRAGS_BESTAND.MASCH_NR as MACHINE_NO,
IST_PRI as SCRAP_COUNT, GRUND_TEXT as SCRAP_DESIGNATIONREASON,
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
AUFTRAGS_BESTAND.BEARB_DATE between TO_DATE('10-09-2015 20:00:00', 'DD-MM-YYYY HH24:MI:SS') and TO_DATE(TO_CHAR(CURRENT_DATE, 'YYYY-MM-DD'),'YYYY-MM-DD');
