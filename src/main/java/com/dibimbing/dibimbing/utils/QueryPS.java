package com.dibimbing.dibimbing.utils;

import org.springframework.stereotype.Component;

@Component
public class QueryPS {
    public String getByIDSP = "CREATE OR REPLACE FUNCTION public.getbyidfunction(rqid integer)\n" +
            " RETURNS TABLE(resid bigint, resnama character varying, ressatuan character varying, resstok integer, resharga double precision)\n" +
            " LANGUAGE plpgsql\n" +
            "AS $function$\n" +
            "BEGIN\n" +
            "  RETURN QUERY\n" +
            "    select e.id, e.nama, e.satuan,e.stok,e.harga\n" +
            "    FROM public.barang AS e\n" +
            "    WHERE id = rqId;\n" +
            "END;\n" +
            "$function$\n" +
            ";\n";

    public String testSP = "CREATE OR REPLACE PROCEDURE public.testsp(IN rqid character varying, OUT resid character varying)\n" +
            " LANGUAGE plpgsql\n" +
            "AS $procedure$\n" +
            "\tBEGIN\n" +
            "      resid =rqid;\n" +
            "\tEND;\n" +
            "$procedure$\n" +
            ";\n";

    public String getBarang1 = "CREATE OR REPLACE FUNCTION public.getbarang1(rqnama character varying)\n" +
            " RETURNS TABLE(resid integer, resnama character varying, resstok character varying, resharga integer, ressatuan character varying)\n" +
            " LANGUAGE plpgsql\n" +
            "AS $function$\n" +
            "DECLARE \n" +
            "    var_r record;\n" +
            "BEGIN\n" +
            "    FOR var_r IN(SELECT \n" +
            "                id,\n" +
            "                nama,\n" +
            "                stok,\n" +
            "                harga,\n" +
            "                satuan\n" +
            "                FROM barang\n" +
            "                WHERE nama ILIKE  rqNama)  \n" +
            "    LOOP\n" +
            "        resid :=  var_r.id  ; \n" +
            "        resnama := var_r.nama ; \n" +
            "        resstok :=  var_r.stok ; \n" +
            "        resharga := var_r.harga  ; \n" +
            "        ressatuan :=  var_r.satuan  ; \n" +
            "        RETURN NEXT;\n" +
            "    END LOOP;\n" +
            "END; $function$\n" +
            ";\n";

    public String updatebarang ="CREATE OR REPLACE PROCEDURE public.updatebarang(IN rqharga double precision, IN rqnama character varying, IN rqsatuan character varying, IN rqstok integer, INOUT resid integer)\n" +
            " LANGUAGE plpgsql\n" +
            "AS $procedure$\n" +
            "\tbegin\n" +
            "\t\n" +
            "\t\tIf not Exists(select id from barang b  where id = resid) Then \n" +
            "\t\t  raise notice 'id tidak ada';\n" +
            "\t\t return;\n" +
            "\t\telse \n" +
            "\t\t raise notice 'id  ada';\n" +
            "\t\tend if;\n" +
            "\t\t\n" +
            "\t\t\n" +
            "\tupdate public.barang\n" +
            "    set harga = rqharga,   nama = rqnama,  satuan = rqsatuan,  stok = rqstok\n" +
            "    where id = resid  returning id into resid; \n" +
            "     commit;\n" +
            "\tEND;\n" +
            "$procedure$\n" +
            ";\n";

    public String deletebarang= "CREATE OR REPLACE PROCEDURE public.deletebarang(IN rqid integer)\n" +
            " LANGUAGE plpgsql\n" +
            "AS $procedure$\n" +
            "\tbegin\t\n" +
            "\tdelete from  barang where id = rqid;\n" +
            "     commit;\n" +
            "\tEND;\n" +
            "$procedure$\n" +
            ";\n";

    public String updatebarangoutwitheror = "CREATE OR REPLACE PROCEDURE public.updatebarangoutwitheror(IN rqharga double precision, IN rqnama character varying, IN rqsatuan character varying, IN rqstok integer, INOUT resid integer, INOUT resharga double precision, INOUT resnama character varying, INOUT ressatuan character varying, INOUT resstok integer, INOUT reserordesc character varying, INOUT reserorcode integer)\n" +
            " LANGUAGE plpgsql\n" +
            "AS $procedure$\n" +
            "\tbegin\n" +
            "\t\tIf not Exists(select id from barang b  where id = resid) Then \n" +
            "\t\t  raise notice 'id tidak ada';\n" +
            "\t\t reserordesc = 'id tidak ditemukan';\n" +
            "\t\t reserorcode = '404';\n" +
            "\t\t return;\n" +
            "\t\telse \n" +
            "\t\t raise notice 'id  ada';\n" +
            "\t\tend if;\n" +
            "\t\n" +
            "\tupdate public.barang\n" +
            "    set harga = rqharga,   nama = rqnama,  satuan = rqsatuan,  stok = rqstok\n" +
            "    where id = resid  returning id into resid; \n" +
            "    resharga = rqharga;\n" +
            "    resnama =rqnama;\n" +
            "    ressatuan =rqsatuan;\n" +
            "    resstok =rqstok;\n" +
            "   \n" +
            "    reserordesc = 'sukses';\n" +
            "\treserorcode = '200';\n" +
            "     commit;\n" +
            "\tEND;\n" +
            "$procedure$\n" +
            ";\n";

    public String testLong = "CREATE OR REPLACE PROCEDURE public.testsplong(IN rqid bigint, INOUT resid bigint)\n" +
            " LANGUAGE plpgsql\n" +
            "AS $procedure$\n" +
            "\tBEGIN\n" +
            "      resid =rqid;\n" +
            "\tEND;\n" +
            "$procedure$\n" +
            ";\n";

    public String testDouble = "CREATE OR REPLACE PROCEDURE public.testspdouble(IN rqid DOUBLE PRECISION, INOUT resid DOUBLE PRECISION)\n" +
            " LANGUAGE plpgsql\n" +
            "AS $procedure$\n" +
            "\tBEGIN\n" +
            "      resid =rqid;\n" +
            "\tEND;\n" +
            "$procedure$\n" +
            ";\n";

    public String testInteger = "CREATE OR REPLACE PROCEDURE public.testspInteger(IN rqid integer, INOUT resid integer)\n" +
            " LANGUAGE plpgsql\n" +
            "AS $procedure$\n" +
            "\tBEGIN\n" +
            "      resid =rqid;\n" +
            "\tEND;\n" +
            "$procedure$\n" +
            ";\n";

    public String save ="CREATE OR REPLACE PROCEDURE public.savebarangoutwitheror(IN rqharga double precision, IN rqnama character varying, IN rqsatuan character varying, IN rqstok integer, INOUT resid integer, INOUT resharga double precision, INOUT resnama character varying, INOUT ressatuan character varying, INOUT resstok integer, INOUT reserordesc character varying, INOUT reserorcode integer)\n" +
            " LANGUAGE plpgsql\n" +
            "AS $procedure$\n" +
            "\tbegin\n" +
            "\t\tif rqnama is null Then \n" +
            "\t\t  raise notice 'nama kosong';\n" +
            "\t\t reserordesc = 'nama wajib diisi';\n" +
            "\t\t reserorcode = 404;\n" +
            "\t\t return;\n" +
            "\t\telse \n" +
            "\t\t raise notice 'nama  ada';\n" +
            "\t\tend if; \n" +
            "\t\n" +
            "     INSERT into public.barang\n" +
            "     (id,harga,nama,satuan,stok,created_date,updated_date) \n" +
            "     SELECT nextval('barang_id_seq'),\n" +
            "           rqharga,\n" +
            "           rqnama,\n" +
            "           rqsatuan,\n" +
            "           rqstok,\n" +
            "           NOW(),NOW()\n" +
            "    RETURNING id INTO resid;\n" +
            "    resharga = rqharga;\n" +
            "    resnama =rqnama;\n" +
            "    ressatuan =rqsatuan;\n" +
            "    resstok =rqstok;\n" +
            "   \n" +
            "    reserordesc = 'sukses';\n" +
            "\treserorcode = 200;\n" +
            "     commit;\n" +
            "\tEND;\n" +
            "$procedure$\n" +
            ";\n";
}
