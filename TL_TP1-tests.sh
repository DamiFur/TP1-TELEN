#!/bin/bash
java AFD.java -leng pruebas/regex/un_simbolo.regex -aut test2.aut
java AFD.java -leng pruebas/regex/dos_veces_un_simbolo.regex -aut test3.aut
java AFD.java -leng pruebas/regex/un_simbolo_estrella.regex -aut test4.aut
java AFD.java -leng pruebas/regex/un_simbolo_mas.regex -aut test5.aut

java AFD.java -leng pruebas/regex/un_simbolo_opcional.regex -aut test6.aut
java AFD.java -leng pruebas/regex/un_simbolo_u_otro.regex -aut test7.aut
java AFD.java -leng pruebas/regex/ej1a_p4.regex -aut test8.aut
java AFD.java -leng pruebas/regex/ej1b_p4.regex -aut test9.aut

# Test Intersección
java AFD.java -intersec -aut1 pruebas/automatas/a_o_b.aut -aut2 pruebas/automatas/a_o_c.aut -aut test18.aut
java AFD.java -intersec -aut1 pruebas/automatas/a_o_b.aut -aut2 pruebas/automatas/c_o_d.aut -aut test19.aut
java AFD.java -intersec -aut1 pruebas/automatas/a_o_b_alfabeto_abcd.aut -aut2 pruebas/automatas/a_o_c_alfabeto_abcd.aut -aut test20.aut
java AFD.java -intersec -aut1 pruebas/automatas/a_o_b_alfabeto_abcd.aut -aut2 pruebas/automatas/c_o_d_alfabeto_abcd.aut -aut test21.aut

echo "* Test Intersección - casa/casado"
java AFD.java -intersec -aut1 pruebas/automatas/casa_o_casado.aut -aut2 pruebas/automatas/casa.aut -aut test22.aut
java AFD.java -intersec -aut1 pruebas/automatas/casa.aut -aut2 pruebas/automatas/casa_o_casado.aut -aut test22b.aut
java AFD.java -aut test22.aut "casa"; echo -e "TRUE\n"
java AFD.java -aut test22.aut "casado"; echo -e "FALSE\n"
java AFD.java -aut test22.aut "casad"; echo -e "FALSE\n"
java AFD.java -aut test22b.aut "casa"; echo -e "TRUE\n"
java AFD.java -aut test22b.aut "casado"; echo -e "FALSE\n"
java AFD.java -aut test22b.aut "casad"; echo -e "FALSE\n"

echo "* Test Intersección - casa vs. casado->vacío"

java AFD.java -intersec -aut1 pruebas/automatas/casado.aut -aut2 pruebas/automatas/casa.aut -aut test23.aut
java AFD.java -aut test23.aut "casa"; echo -e "FALSE\n"
java AFD.java -aut test23.aut "casado"; echo -e "FALSE\n"

echo "* Test Intersección - casa vs. casa"
java AFD.java -intersec -aut1 pruebas/automatas/casa.aut -aut2 pruebas/automatas/casa.aut -aut test24.aut
java AFD.java -aut test24.aut ""; echo -e "FALSE\n"
java AFD.java -aut test24.aut "casa"; echo -e "TRUE\n"
java AFD.java -aut test24.aut "casado"; echo -e "FALSE\n"
java AFD.java -aut test24.aut "cas"; echo -e "FALSE\n"
java AFD.java -aut test24.aut " "; echo -e "FALSE\n"

java AFD.java -complemento -aut1 pruebas/automatas/cantidad_par_ceros.aut -aut test25.aut
java AFD.java -complemento -aut1 pruebas/automatas/un_simbolo_estrella.aut -aut test26.aut

echo "* Test Complemento - Vacío"
java AFD.java -complemento -aut1 pruebas/automatas/vacio.aut -aut test27.aut
java AFD.java -aut test27.aut "b"; echo -e "FALSE\n"
java AFD.java -aut test27.aut " "; echo -e "FALSE\n"
java AFD.java -aut test27.aut "1"; echo -e "FALSE\n"
java AFD.java -aut test27.aut ""; echo -e "TRUE\n"
java AFD.java -aut test27.aut "a"; echo -e "TRUE\n"
java AFD.java -aut test27.aut "aa"; echo -e "TRUE\n"
java AFD.java -aut test27.aut "aaaa"; echo -e "TRUE\n"

echo "* Test Complemento - Un símbolo c/alfabeto extendido"
java AFD.java -complemento -aut1 pruebas/automatas/simbolos_de_mas.aut -aut test28.aut
java AFD.java -aut test28.aut " "; echo -e "FALSE\n"
java AFD.java -aut test28.aut "1"; echo -e "FALSE\n"
java AFD.java -aut test28.aut "a"; echo -e "FALSE\n"
java AFD.java -aut test28.aut "b"; echo -e "FALSE\n"
java AFD.java -aut test28.aut ""; echo -e "TRUE\n"
java AFD.java -aut test28.aut "aa"; echo -e "TRUE\n"
java AFD.java -aut test28.aut "cdd"; echo -e "TRUE\n"


echo "* Test Equivalencia - Mismo autómata"
java AFD.java -equival -aut1 pruebas/automatas/casado.aut -aut2 pruebas/automatas/casado.aut; echo -e "TRUE+\n" 
java AFD.java -equival -aut1 pruebas/automatas/casa.aut -aut2 pruebas/automatas/casa.aut; echo -e "TRUE+\n"
java AFD.java -equival -aut1 pruebas/automatas/vacio.aut -aut2 pruebas/automatas/vacio.aut; echo -e "TRUE+\n" 
java AFD.java -equival -aut1 pruebas/automatas/ej1b_p4.aut -aut2 pruebas/automatas/ej1b_p4.aut; echo -e "TRUE+\n"
java AFD.java -equival -aut1 pruebas/automatas/ej1a_p4.aut -aut2 pruebas/automatas/ej1a_p4.aut; echo -e "TRUE+\n"
java AFD.java -equival -aut1 pruebas/automatas/un_simbolo_estrella.aut -aut2 pruebas/automatas/un_simbolo_estrella.aut; echo -e "TRUE+\n" 

echo "* Test Equivalencia - Distintos autómatas"
java AFD.java -equival -aut1 pruebas/automatas/un_simbolo_estrella.aut -aut2 pruebas/automatas/un_simbolo_mas.aut; echo -e "FALSE+\n" 
java AFD.java -equival -aut1 pruebas/automatas/un_simbolo_estrella.aut -aut2 pruebas/automatas/vacio.aut; echo -e "FALSE+\n"
java AFD.java -equival -aut1 pruebas/automatas/vacio.aut -aut2 pruebas/automatas/un_simbolo_estrella.aut; echo -e "FALSE+\n" 
java AFD.java -equival -aut1 pruebas/automatas/un_simbolo_mas.aut -aut2 pruebas/automatas/un_simbolo_estrella.aut; echo -e "FALSE+\n"
java AFD.java -equival -aut1 pruebas/automatas/un_simbolo_mas.aut -aut2 pruebas/automatas/vacio.aut; echo -e "FALSE+\n"
java AFD.java -equival -aut1 pruebas/automatas/vacio.aut -aut2 pruebas/automatas/un_simbolo_mas.aut; echo -e "FALSE+\n" 
java AFD.java -equival -aut1 pruebas/automatas/ej1a_p4.aut -aut2 pruebas/automatas/ej1b_p4.aut; echo -e "FALSE+\n" 
java AFD.java -equival -aut1 pruebas/automatas/ej1b_p4.aut -aut2 pruebas/automatas/ej1a_p4.aut; echo -e "FALSE+\n"

echo "* Test Equivalencia - Casa/Casado"
java AFD.java -equival -aut1 pruebas/automatas/casa.aut -aut2 pruebas/automatas/casado.aut; echo -e "FALSE+\n"
java AFD.java -equival -aut1 pruebas/automatas/casa.aut -aut2 pruebas/automatas/casa_o_casado.aut; echo -e "FALSE+\n"
java AFD.java -equival -aut1 pruebas/automatas/casa_o_casado.aut -aut2 pruebas/automatas/casa.aut; echo -e "FALSE+\n"
java AFD.java -equival -aut1 pruebas/automatas/casado.aut -aut2 pruebas/automatas/casa.aut; echo -e "FALSE+\n"
java AFD.java -equival -aut1 pruebas/automatas/casado.aut -aut2 pruebas/automatas/casa_o_casado.aut; echo -e "FALSE+\n"
java AFD.java -equival -aut1 pruebas/automatas/casa_o_casado.aut -aut2 pruebas/automatas/casado.aut; echo -e "FALSE+\n"

echo "* Test autómatas - Un símbolo"
java AFD.java -aut pruebas/automatas/un_simbolo.aut "a"; echo -e "TRUE\n"
java AFD.java -aut pruebas/automatas/un_simbolo.aut "a "; echo -e "FALSE\n"
java AFD.java -aut pruebas/automatas/un_simbolo.aut " a"; echo -e "FALSE\n"
java AFD.java -aut pruebas/automatas/un_simbolo.aut " "; echo -e "FALSE\n"
java AFD.java -aut pruebas/automatas/un_simbolo.aut "aa"; echo -e "FALSE\n"
java AFD.java -aut pruebas/automatas/un_simbolo.aut "abc"; echo -e "FALSE\n"

echo "* Test autómatas - Dos veces un símbolo"
java AFD.java -aut pruebas/automatas/dos_veces_un_simbolo.aut "aa"; echo -e "TRUE\n"
java AFD.java -aut pruebas/automatas/dos_veces_un_simbolo.aut "a a"; echo -e "FALSE\n"
java AFD.java -aut pruebas/automatas/dos_veces_un_simbolo.aut "bb"; echo -e "FALSE\n"
java AFD.java -aut pruebas/automatas/dos_veces_un_simbolo.aut "aba"; echo -e "FALSE\n"
java AFD.java -aut pruebas/automatas/dos_veces_un_simbolo.aut "  "; echo -e "FALSE\n"
java AFD.java -aut pruebas/automatas/dos_veces_un_simbolo.aut ""; echo -e "FALSE\n"

echo "* Test autómatas - Un símbolo*"
java AFD.java -aut pruebas/automatas/un_simbolo_estrella.aut ""; echo -e "TRUE\n"
java AFD.java -aut pruebas/automatas/un_simbolo_estrella.aut " "; echo -e "FALSE\n"
java AFD.java -aut pruebas/automatas/un_simbolo_estrella.aut "a"; echo -e "TRUE\n"
java AFD.java -aut pruebas/automatas/un_simbolo_estrella.aut "aa"; echo -e "TRUE\n"
java AFD.java -aut pruebas/automatas/un_simbolo_estrella.aut "aaaa"; echo -e "TRUE\n"
java AFD.java -aut pruebas/automatas/un_simbolo_estrella.aut "aa aa"; echo -e "FALSE\n"
java AFD.java -aut pruebas/automatas/un_simbolo_estrella.aut "\t"; echo -e "FALSE\n"
java AFD.java -aut pruebas/automatas/un_simbolo_estrella.aut "e"; echo -e "FALSE\n"

echo "* Test autómatas - Un símbolo+"
java AFD.java -aut pruebas/automatas/un_simbolo_mas.aut ""; echo -e "FALSE\n"
java AFD.java -aut pruebas/automatas/un_simbolo_mas.aut " "; echo -e "FALSE\n"
java AFD.java -aut pruebas/automatas/un_simbolo_mas.aut "a"; echo -e "TRUE\n"
java AFD.java -aut pruebas/automatas/un_simbolo_mas.aut "aa"; echo -e "TRUE\n"
java AFD.java -aut pruebas/automatas/un_simbolo_mas.aut "aaaa"; echo -e "TRUE\n"
java AFD.java -aut pruebas/automatas/un_simbolo_mas.aut "aa aa"; echo -e "FALSE\n"
java AFD.java -aut pruebas/automatas/un_simbolo_mas.aut "\t"; echo -e "FALSE\n"
java AFD.java -aut pruebas/automatas/un_simbolo_mas.aut "e"; echo -e "FALSE\n"

echo "* Test autómatas - Un símbolo?"
java AFD.java -aut pruebas/automatas/un_simbolo_opcional.aut "a"; echo -e "TRUE\n"
java AFD.java -aut pruebas/automatas/un_simbolo_opcional.aut " a"; echo -e "FALSE\n"
java AFD.java -aut pruebas/automatas/un_simbolo_opcional.aut "a "; echo -e "FALSE\n"
java AFD.java -aut pruebas/automatas/un_simbolo_opcional.aut "aa"; echo -e "FALSE\n"
java AFD.java -aut pruebas/automatas/un_simbolo_opcional.aut ""; echo -e "TRUE\n"
java AFD.java -aut pruebas/automatas/un_simbolo_opcional.aut " "; echo -e "FALSE\n"

echo "* Test autómatas - Un símbolo u otro"
java AFD.java -aut pruebas/automatas/un_simbolo_u_otro.aut ""; echo -e "FALSE\n"
java AFD.java -aut pruebas/automatas/un_simbolo_u_otro.aut "a"; echo -e "TRUE\n"
java AFD.java -aut pruebas/automatas/un_simbolo_u_otro.aut "b"; echo -e "TRUE\n"
java AFD.java -aut pruebas/automatas/un_simbolo_u_otro.aut "c"; echo -e "FALSE\n"
java AFD.java -aut pruebas/automatas/un_simbolo_u_otro.aut "c "; echo -e "FALSE\n"
java AFD.java -aut pruebas/automatas/un_simbolo_u_otro.aut " a"; echo -e "FALSE\n"
java AFD.java -aut pruebas/automatas/un_simbolo_u_otro.aut "aa"; echo -e "FALSE\n"

echo "* Test autómatas - ej1a_p4"
java AFD.java -aut pruebas/automatas/ej1a_p4.aut "abc"; echo -e "FALSE\n"
java AFD.java -aut pruebas/automatas/ej1a_p4.aut "a"; echo -e "FALSE\n"
java AFD.java -aut pruebas/automatas/ej1a_p4.aut "a "; echo -e "FALSE\n"
java AFD.java -aut pruebas/automatas/ej1a_p4.aut "aaa"; echo -e "TRUE\n"
java AFD.java -aut pruebas/automatas/ej1a_p4.aut "aa"; echo -e "FALSE\n"
java AFD.java -aut pruebas/automatas/ej1a_p4.aut ""; echo -e "FALSE\n"
java AFD.java -aut pruebas/automatas/ej1a_p4.aut "bb"; echo -e "FALSE\n"
java AFD.java -aut pruebas/automatas/ej1a_p4.aut "bbb"; echo -e "FALSE\n"
java AFD.java -aut pruebas/automatas/ej1a_p4.aut "bbbbbbbbbbb"; echo -e "FALSE\n"
java AFD.java -aut pruebas/automatas/ej1a_p4.aut "c"; echo -e "FALSE\n"

echo "* Test autómatas - ej1b_p4"
java AFD.java -aut pruebas/automatas/ej1b_p4.aut ""; echo -e "FALSE\n"
java AFD.java -aut pruebas/automatas/ej1b_p4.aut "a"; echo -e "TRUE\n"
java AFD.java -aut pruebas/automatas/ej1b_p4.aut "aaaaaaa"; echo -e "TRUE\n"
java AFD.java -aut pruebas/automatas/ej1b_p4.aut "bbbbb"; echo -e "TRUE\n"
java AFD.java -aut pruebas/automatas/ej1b_p4.aut "b"; echo -e "TRUE\n"
java AFD.java -aut pruebas/automatas/ej1b_p4.aut "c"; echo -e "FALSE\n"

# Generación de DOTs y de PNGs
echo "* Test DOT - Generación de gráficos"
for aut in `ls pruebas/automatas/*.aut;ls test*.aut`
do 
    java AFD.java -aut $aut -dot $aut.dot
    dot -Tpng $aut.dot -o $aut.png
done
