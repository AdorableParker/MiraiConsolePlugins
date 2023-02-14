package org.nymph

import net.mamoe.mirai.console.data.AutoSavePluginData
import net.mamoe.mirai.console.data.value


object MDAccountData : AutoSavePluginData("MDAccountData") {
    val MDAccountMap: MutableMap<Long, String> by value()

    val songList: Map<String, String> by value(
        mapOf(
            "0-48" to "曲名:Magical Wonderland (More colorful mix)\n谱师:3R2",
            "0-0" to "曲名:Iyaiya\n谱师:小野道ono",
            "0-2" to "曲名:Wonderful Pain\n谱师:Haloweak",
            "0-3" to "曲名:Breaking Dawn\n谱师:TetraCalyx",
            "0-4" to "曲名:单向地铁 Feat.karin\n谱师:小野道ono",
            "0-1" to "曲名:Frost Land\n谱师:Zris",
            "0-5" to "曲名:Heart-Pounding Flight\n谱师:TetraCalyx",
            "0-29" to "曲名:Pancake is Love\n谱师:3R2",
            "0-6" to "曲名:时光涂鸦\n谱师:TetraCalyx",
            "0-37" to "曲名:Evolution\n谱师:Haloweak",
            "0-7" to "曲名:海豚与广播 feat.乌拉喵\n谱师:小野道ono",
            "0-8" to "曲名:雪の雫・雨の音\n谱师:天游 feat.东京塔子",
            "0-43" to "曲名:Best One feat.墨橙\n谱师:小野道ono",
            "0-31" to "曲名:糖果色恋爱学\n谱师:ANK feat.熊子",
            "0-38" to "曲名:Night Wander (cnsouka Remix)\n谱师:cnsouka",
            "0-46" to "曲名:ドーナドーナのうた\n谱师:水夏える feat.月乃",
            "0-9" to "曲名:Spring Carnival\n谱师:3R2",
            "0-30" to "曲名:DISCO NIGHT\n谱师:ANK feat.熊子",
            "0-49" to "曲名:恋のMoonlight\n谱师:REDALiCE feat. 犬山たまき",
            "0-10" to "曲名:恋爱语音导航 feat.yousa\n谱师:小野道ono",
            "0-11" to "曲名:Lights of Muse\n谱师:Ayatsugu_Otowa",
            "0-12" to "曲名:midstream jam\n谱师:MusMus",
            "0-40" to "曲名:你好\n谱师:MusikM",
            "0-13" to "曲名:Confession\n谱师:Haloweak & G.K",
            "0-32" to "曲名:Galaxy Striker\n谱师:M2U",
            "0-14" to "曲名:Departure Road\n谱师:daisan",
            "0-15" to "曲名:Bass Telekinesis\n谱师:Haloweak",
            "0-16" to "曲名:Cage of Almeria\n谱师:a_hisa",
            "0-17" to "曲名:Ira\n谱师:Lactic Acid Bacteria",
            "0-18" to "曲名:Blackest Luxury Car\n谱师:Chicala Lpis",
            "0-19" to "曲名:Medicine of Sing\n谱师:JurokuNeta.",
            "0-20" to "曲名:irregulyze\n谱师:daisan",
            "0-47" to "曲名:クリスマスなんて興味ないけど\n谱师:かめりあ feat.ななひら",
            "0-21" to "曲名:Imaginary World\n谱师:uma",
            "0-22" to "曲名:Dysthymia\n谱师:a_hisa",
            "0-42" to "曲名:新世界より\n谱师:綾奈なな (Prod.Aya2g+Works)",
            "0-33" to "曲名:僞顏\n谱师:天游",
            "0-44" to "曲名:Say!ファンファーレ!\n谱师:白上フブキ",
            "0-34" to "曲名:流星车手\n谱师:iKz feat.漆柚",
            "0-23" to "曲名:Formation\n谱师:SLT",
            "0-24" to "曲名:心層麻酔\n谱师:iKz",
            "0-50" to "曲名:Mezame Eurythmics\n谱师:bermei.inazawa",
            "0-51" to "曲名:Shenri Kuaira -repeat-\n谱师:bermei.inazawa",
            "0-25" to "曲名:Latitude\n谱师:SLT",
            "0-39" to "曲名:Aqua Stars\n谱师:Sound Souler",
            "0-26" to "曲名:粉骨砕身カジノゥ\n谱师:モリモリあつし",
            "0-27" to "曲名:Clock Room & Spiritual World\n谱师:a_hisa",
            "0-52" to "曲名:INTERNET OVERDOSE（Aiobahn feat.KOTOKO）\n谱师:『NEEDY GIRL OVERDOSE』主題歌",
            "0-35" to "曲名:徒 花\n谱师:Aya~亜夜罪~",
            "0-28" to "曲名:無人区-Vacuum Track#ADD8E6-\n谱师:NoKANY",
            "0-36" to "曲名:MilK\n谱师:モリモリあつし",
            "0-41" to "曲名:umpopoff\n谱师:まめこ",
            "0-45" to "曲名:もぺもぺ\n谱师:LeaF",
            "1-0" to "曲名:八月雨后，初晴与彩虹\n谱师:yk!",
            "1-1" to "曲名:魔法数字\n谱师:味素",
            "1-2" to "曲名:Dreaming Girl\n谱师:Nano&板烧鹅尼子",
            "1-3" to "曲名:Daruma-san Fell Over\n谱师:Ncha-P",
            "1-4" to "曲名:Different\n谱师:Ncha-P",
            "1-5" to "曲名:The Future of the Phantom\n谱师:Ncha-P",
            "3-0" to "曲名:Maharajah\n谱师:アリスシャッハと魔法の楽団",
            "3-1" to "曲名:keep on running\n谱师:uma",
            "3-2" to "曲名:Käfig\n谱师:Chicala Lpis",
            "3-3" to "曲名:-+\n谱师:daisan",
            "3-4" to "曲名:天理鶴情\n谱师:a_hisa",
            "3-5" to "曲名:Adjudicatorz-断罪-\n谱师:JurokuNeta.",
            "4-0" to "曲名:MUSEDASH!!!!\n谱师:暖@よみぃ",
            "4-1" to "曲名:Imprinting\n谱师:削除",
            "4-2" to "曲名:Skyward\n谱师:SLT",
            "4-3" to "曲名:La nuit de vif\n谱师:Polymath9",
            "4-4" to "曲名:Bit-alize\n谱师:Yamajet",
            "4-5" to "曲名:GOODTEK(Hyper Edit)\n谱师:EBIMAYO",
            "5-0" to "曲名:Thirty Million Persona\n谱师:アリスシャッハと魔法の楽団",
            "5-1" to "曲名:conflict\n谱师:siromaru + cranky",
            "5-2" to "曲名:演華舞踊 ~Enka Dance Music~\n谱师:uma with モリモリあつし feat.ましろ",
            "5-3" to "曲名:XING\n谱师:ginkiha",
            "5-4" to "曲名:天翔ける蒼穹のセレナーデ\n谱师:Sakamiya feat.あらたきき",
            "5-5" to "曲名:Gift box\n谱师:K.key",
            "6-0" to "曲名:Out of Sense\n谱师:Sound Souler",
            "6-1" to "曲名:My Life Is For You\n谱师:HyuN feat.Yu-A",
            "6-2" to "曲名:Etude -Sunset-\n谱师:Polymath9",
            "6-3" to "曲名:翘班\n谱师:KillerBlood",
            "6-4" to "曲名:Stargazer\n谱师:a_hisa",
            "6-5" to "曲名:Lys Tourbillon\n谱师:Chicala Lpis",
            "7-0" to "曲名:Brave My Soul\n谱师:uma feat.ましろ",
            "7-1" to "曲名:Halcyon\n谱师:xi",
            "7-2" to "曲名:Crimson Nightingle\n谱师:VeetaCrush",
            "7-3" to "曲名:Invader\n谱师:SLT",
            "7-4" to "曲名:Lyrith -迷宮リリス-\n谱师:ユメミド",
            "7-5" to "曲名:GOODBOUNCE (Groove Edit)\n谱师:EBIMAYO",
            "8-0" to "曲名:Altale\n谱师:削除",
            "8-1" to "曲名:Brain Power\n谱师:NOMA",
            "8-2" to "曲名:Berry Go!!\n谱师:Freezer feat.妃苺",
            "8-3" to "曲名:Sweet* Witch* Girl*\n谱师:モリモリあつし vs. uma",
            "8-4" to "曲名:trippers feeling!\n谱师:KAH",
            "8-5" to "曲名:Lilith ambivalence lovers\n谱师:ikaruga_nex",
            "9-0" to "曲名:Leave It Alone\n谱师:a_hisa",
            "9-1" to "曲名:翼の折れた天使たちのレクイエム\n谱师:Sakamiya feat.前野",
            "9-2" to "曲名:Chronomia\n谱师:Lime",
            "9-3" to "曲名:Dandelion's Daydream\n谱师:VeetaCrush",
            "9-4" to "曲名:ロリキート ~Flat design~\n谱师:黄泉路テヂーモ",
            "9-5" to "曲名:GOODRAGE\n谱师:EBIMAYO",
            "10-0" to "曲名:Destr0yer\n谱师:削除 feat.Nikki Simmons",
            "10-1" to "曲名:Noël\n谱师:モリモリあつし vs. uma",
            "10-2" to "曲名:狂喜蘭舞\n谱师:LeaF",
            "10-3" to "曲名:Two Phace\n谱师:litmus* vs Sound Souler",
            "10-4" to "曲名:Fly Again\n谱师:HyuN & Ritoru",
            "10-5" to "曲名:ouroVoros\n谱师:Negentropy",
            "11-0" to "曲名:Brave My Heart\n谱师:uma feat.ましろ",
            "11-1" to "曲名:Sakura Fubuki\n谱师:Street",
            "11-2" to "曲名:8bit Adventurer\n谱师:Lime",
            "11-3" to "曲名:Suffering of screw\n谱师:Sakamiya feat.由地波瑠",
            "11-4" to "曲名:tiny lady\n谱师:Chicala Lpis",
            "11-5" to "曲名:Power Attack\n谱师:EBIMAYO",
            "12-0" to "曲名:蓋棺クリサリス\n谱师:Team Grimoire feat.Sennzai",
            "12-1" to "曲名:Sterelogue\n谱师:VeetaCrush",
            "12-2" to "曲名:Cheshire's Dance\n谱师:a_hisa",
            "12-3" to "曲名:Skrik\n谱师:Chicala Lpis",
            "12-4" to "曲名:Soda Pop Canva5!\n谱师:モリモリあつし vs. XIzE",
            "12-5" to "曲名:ЯUBY:LINTe\n谱师:orangentle",
            "13-0" to "曲名:速溶霓虹 feat.熊子\n谱师:ANK",
            "13-1" to "曲名:星球上的追溯诗\n谱师:味素 feat.熊子",
            "13-2" to "曲名:我要买买买\n谱师:iKz",
            "13-3" to "曲名:约会宣言\n谱师:iKz feat.Hanser",
            "13-4" to "曲名:初雪\n谱师:KyuRu☆ feat.清塚幽",
            "13-5" to "曲名:心上华海\n谱师:Nekock·LK",
            "14-0" to "曲名:Elysion's Old Mans\n谱师:アリスシャッハと魔法の楽団",
            "14-1" to "曲名:AXION\n谱师:削除",
            "14-2" to "曲名:Amnesia\n谱师:a_hisa",
            "14-3" to "曲名:温泉大作戦\n谱师:Freezer feat.妃苺",
            "14-4" to "曲名:Gleam stone\n谱师:adaptor",
            "14-5" to "曲名:GOODWORLD\n谱师:EBIMAYO",
            "15-0" to "曲名:魔咒 feat.早木旋子\n谱师:ANK",
            "15-1" to "曲名:斑斓星，彩绘，旅行诗\n谱师:cnsouka Feat.karin",
            "15-2" to "曲名:Satell Knight\n谱师:UncleWang Feat.熊子",
            "15-3" to "曲名:Black River Feat.Mes\n谱师:小野道ono",
            "15-4" to "曲名:生而为人，我很抱歉\n谱师:Nekock·LK",
            "15-5" to "曲名:飢えた鳥たち\n谱师:MusikM",
            "16-0" to "曲名:Future Dive\n谱师:Cosmograph",
            "16-1" to "曲名:Re：End of a Dream\n谱师:uma vs. モリモリあつし",
            "16-2" to "曲名:Etude -Storm-\n谱师:Polymath9",
            "16-3" to "曲名:Unlimited Katharsis\n谱师:Reku Mochizuki",
            "16-4" to "曲名:Magic Knight Girl\n谱师:Nizikawa",
            "16-5" to "曲名:Eeliaas\n谱师:ミツキ",
            "17-0" to "曲名:Cotton Candy Wonderland\n谱师:Endorfin.",
            "17-1" to "曲名:プナイプナイたいそう\n谱师:立秋 feat.ちょこ",
            "17-2" to "曲名:Fly↑High\n谱师:P4koo(feat.rerone)",
            "17-3" to "曲名:prejudice\n谱师:a_hisa",
            "17-4" to "曲名:The 89's Momentum\n谱师:MYUKKE.",
            "17-5" to "曲名:energy night(DASH mix)\n谱师:KAH",
            "18-0" to "曲名:SWEETSWEETSWEET\n谱师:砂糖协会（ANK feat.熊子）",
            "18-1" to "曲名:深蓝与夜的呼吸\n谱师:砂糖协会（ANK feat.熊子）",
            "18-2" to "曲名:Joy Connection\n谱师:MusikM / Jun Kuroda",
            "18-3" to "曲名:任性 Ver.B\n谱师:小野道ono",
            "18-4" to "曲名:就是不听话\n谱师:iKz feat.Hanser",
            "18-5" to "曲名:草蛇惊一\n谱师:麦当叔劳劳",
            "19-0" to "曲名:INFINITY\n谱师:Cosmograph",
            "19-1" to "曲名:プナイプナイせんそう\n谱师:立秋 feat.ちょこ",
            "19-2" to "曲名:Maxi\n谱师:Nizikawa",
            "19-3" to "曲名:YInMn Blue\n谱师:siqlo",
            "19-4" to "曲名:Plumage\n谱师:brz1128",
            "19-5" to "曲名:Dr.Techro\n谱师:米線p.",
            "20-0" to "曲名:Moonlight Banquet\n谱师:a_hisa",
            "20-1" to "曲名:Flashdance\n谱师:litmus*",
            "20-2" to "曲名:INFiNiTE ENERZY -Overdoze-\n谱师:Reku Mochizuki",
            "20-3" to "曲名:One Way Street\n谱师:siqlo",
            "20-4" to "曲名:This Club is Not 4 U\n谱师:EmoCosine",
            "20-5" to "曲名:ULTRA MEGA HAPPY PARTY!!!\n谱师:翡乃イスカ",
            "21-0" to "曲名:Glimmer feat.祈inory\n谱师:kors k",
            "21-1" to "曲名:EXIST feat.米雅\n谱师:G.K & Haloweak",
            "21-2" to "曲名:Irreplaceable feat.夏铜子\n谱师:Haloweak",
            "22-0" to "曲名:The NightScape\n谱师:litmus*",
            "22-1" to "曲名:FREEDOM DiVE↓\n谱师:xi",
            "22-2" to "曲名:Φ\n谱师:Street",
            "22-3" to "曲名:Lueur de la nuit\n谱师:ああああ + しーけー",
            "22-4" to "曲名:Creamy Sugary OVERDRIVE!!!\n谱师:BTB",
            "22-5" to "曲名:Disorder (feat.YURI)\n谱师:HyuN",
            "23-0" to "曲名:雨后甜点\n谱师:砂糖协会（ANK feat.熊子）",
            "23-1" to "曲名:告白应援方程式\n谱师:S9ryne Feat.祈Inory",
            "23-2" to "曲名:Omatsuri feat.兔子ST\n谱师:Nekock·LK",
            "23-3" to "曲名:FUTUREPOP\n谱师:砂糖协会（ANK feat.熊子）",
            "23-4" to "曲名:The Breeze\n谱师:sctl feat.Syepias",
            "23-5" to "曲名:ウォー・アイ・レタス炒飯！！\n谱师:諸星なな feat.加藤はるか&廣瀬祐輝",
            "24-0" to "曲名:The Last Page\n谱师:ARForest",
            "24-1" to "曲名:IKAROS\n谱师:ETIA. feat.Jenga",
            "24-2" to "曲名:月夜見\n谱师:Halv",
            "24-3" to "曲名:Future Stream\n谱师:Qutabire",
            "24-4" to "曲名:FULi AUTO SHOOTER\n谱师:MYUKKE.",
            "24-5" to "曲名:GOODFORTUNE\n谱师:EBIMAYO",
            "25-0" to "曲名:tape/stop/night\n谱师:砂糖协会",
            "25-1" to "曲名:Pixel Galaxy\n谱师:Snail's House",
            "25-2" to "曲名:Notice\n谱师:Moe Shop / TORIENA",
            "25-3" to "曲名:<b>sᴛʀᴀᴡʙᴇʀʀʏ ɢᴏᴅᴢɪʟʟᴀ</b>\n谱师:砂糖协会",
            "25-4" to "曲名:OKIMOCHI EXPRESSION\n谱师:ディスコブラザーズ",
            "25-5" to "曲名:君とpool disco\n谱师:loopcoda",
            "26-0" to "曲名:東方兔傳說 -SKY DEFENDER-\n谱师:sctl",
            "26-1" to "曲名:ENERGY SYNERGY MATRIX\n谱师:Tanchiky",
            "26-2" to "曲名:プナイプナイげんそう ～プナイプナイinワンダーランド～\n谱师:立秋 feat.ちょこ",
            "26-3" to "曲名:Better Graphic Animation\n谱师:ルゼ",
            "26-4" to "曲名:Variant Cross\n谱师:M-UE",
            "26-5" to "曲名:Ultra Happy Miracle Bazoooooka!!\n谱师:翡乃イスカ vs. 梅干茶漬け",
            "27-0" to "曲名:ベースラインやってる?笑\n谱师:かめりあ feat.ななひら",
            "27-1" to "曲名:ゲーミング☆Everything\n谱师:かめりあ feat.ななひら",
            "27-2" to "曲名:レンジで好吃☆電子調理器使用中華料理四千年歴史瞬間調理完了武闘的料理長☆\n谱师:かめりあ feat.ななひら",
            "27-3" to "曲名:You Make My Life 1UP\n谱师:かめりあ feat.ななひら",
            "27-4" to "曲名:ニワカ三年オタ八年、インターネッツはforever\n谱师:かめりあ feat.ななひら",
            "27-5" to "曲名:お願い！コンコンお稲荷さま\n谱师:ARM×狐夢想 feat.ななひら",
            "28-0" to "曲名:弊社御社\n谱师:ヒゲドライバー",
            "28-1" to "曲名:Ginevra\n谱师:MYUKKE.",
            "28-2" to "曲名:Paracelestia\n谱师:Nego_tiator",
            "28-3" to "曲名:un secret\n谱师:モリモリあつし",
            "28-4" to "曲名:Good Life\n谱师:litmus*",
            "28-5" to "曲名:ニニ\n谱师:テヅカ × Qayo",
            "2-0" to "曲名:Oriens\n谱师:ginkiha",
            "2-1" to "曲名:PUPA\n谱师:モリモリあつし",
            "2-2" to "曲名:Luna Express 2032\n谱师:Sakamiya feat.小宮真央",
            "2-3" to "曲名:浮世絵横丁\n谱师:魔界都市ニイガタ",
            "2-4" to "曲名:Alice in Misanthrope -厭世アリス-\n谱师:LeaF",
            "2-5" to "曲名:GOODMEN\n谱师:EBIMAYO",
            "29-0" to "曲名:Groove Prayer\n谱师:COSIO (ZUNTATA) feat. ChouCho",
            "29-1" to "曲名:FUJIN Rumble\n谱师:COSIO(ZUNTATA)",
            "29-2" to "曲名:Marry me, Nightmare\n谱师:t+pazolite",
            "29-3" to "曲名:HG魔改造ポリビニル少年\n谱师:IOSYS TRAX (D.watt with さきぴょ)",
            "29-4" to "曲名:聖者の息吹\n谱师:世阿弥 vs Tatsh",
            "29-5" to "曲名:ouroboros -twin stroke of the end-\n谱师:Cranky VS MASAKI",
            "30-0" to "曲名:Girly Cupid\n谱师:PSYQUI feat. Marpril",
            "30-1" to "曲名:sheep in the light\n谱师:Marpril",
            "30-2" to "曲名:ブレーカーシティ\n谱师:HAMA feat. Marpril",
            "30-3" to "曲名:heterodoxy\n谱师:memex",
            "30-4" to "曲名:コンピューターミュージックガール\n谱师:ミディ",
            "30-5" to "曲名:焦点 feat.早木旋子\n谱师:ANK",
            "31-0" to "曲名:The 90's Decision\n谱师:MYUKKE.",
            "31-1" to "曲名:Medusa\n谱师:HiTECH NINJA",
            "31-2" to "曲名:Final Step!\n谱师:Lime",
            "31-3" to "曲名:MAGENTA POTION\n谱师:EmoCosine",
            "31-4" to "曲名:Cross†Ray (feat. 月下Lia)\n谱师:HyuN",
            "31-5" to "曲名:Square Lake\n谱师:ミツキ",
            "32-0" to "曲名:プレパララ\n谱师:水夏える",
            "32-1" to "曲名:Whatcha;Whatcha Doin'\n谱师:水夏える",
            "32-2" to "曲名:斑\n谱师:Fumihisa Tanaka",
            "32-3" to "曲名:pICARESq\n谱师:水夏える",
            "32-4" to "曲名:Desastre\n谱师:水夏える",
            "32-5" to "曲名:Shoot for the Moon\n谱师:水夏える",
            "33-0" to "曲名:Fireflies (Funk Fiction remix)\n谱师:Nikki Simmons",
            "33-1" to "曲名:Light up my love!!\n谱师:onoken feat. moco",
            "33-2" to "曲名:Happiness Breeze\n谱师:3R2 as DJ Mashiro",
            "33-3" to "曲名:Chrome VOX\n谱师:t+pazolite",
            "33-4" to "曲名:CHAOS\n谱师:Æsir",
            "33-5" to "曲名:Saika\n谱师:Rabpit",
            "33-6" to "曲名:Standby for Action\n谱师:3R2",
            "33-7" to "曲名:Hydrangea\n谱师:Tatsh",
            "33-8" to "曲名:Amenemhat\n谱师:Yesod (litmus* + Rabbit House + A. Ki)",
            "33-9" to "曲名:三灯火\n谱师:yuritoworks.",
            "33-10" to "曲名:「妖怪録、我し来にけり。」\n谱师:TEZUKApo11o band ft.ランコ(豚乙女)+OFFICE萬田㈱",
            "33-11" to "曲名:Blah!!\n谱师:HiTECH NINJA",
            "33-12" to "曲名:CHAOS (Glitch)\n谱师:Æsir",
            "34-0" to "曲名:ALiVE\n谱师:REDALiCE",
            "34-1" to "曲名:BATTLE NO.1\n谱师:TANO*C Sound Team",
            "34-2" to "曲名:Cthugha\n谱师:USAO",
            "34-3" to "曲名:TWINKLE★MAGIC\n谱师:P*Light",
            "34-4" to "曲名:Comet Coaster\n谱师:DJ Noriken & aran",
            "34-5" to "曲名:XODUS\n谱师:DJ Myosuke & Gram",
            "35-0" to "曲名:MuseDashを作っているPeroPeroGamesさんが倒産しちゃったよ～\n谱师:立秋 feat.ちょこ",
            "35-1" to "曲名:MARENOL\n谱师:LeaF",
            "35-2" to "曲名:僕の和风本当上手\n谱师:老爷",
            "35-3" to "曲名:Rush B\n谱师:iKz",
            "35-4" to "曲名:DataErr0r\n谱师:Cosmograph",
            "35-5" to "曲名:Burn\n谱师:NceS",
            "36-0" to "曲名:NightTheater\n谱师:わかどり",
            "36-1" to "曲名:Cutter\n谱师:EmoCosine",
            "36-2" to "曲名:竹\n谱师:立秋 feat.ちょこ",
            "36-3" to "曲名:enchanted love\n谱师:linear ring",
            "36-4" to "曲名:c.s.q.n.\n谱师:Aoi",
            "36-5" to "曲名:Booouncing!!\n谱师:翡乃イスカ vs. s-don",
            "37-0" to "曲名:琉璃色前奏曲\n谱师:Nekock·LK",
            "37-1" to "曲名:ネオンライト\n谱师:TEMPLIME feat 星宫とと",
            "37-2" to "曲名:花たちに希望を\n谱师:Sound piercer feat.DAZBEE",
            "37-3" to "曲名:5月30日、自転車日和\n谱师:dawn-system feat.おーくん",
            "37-4" to "曲名:SKY↑HIGH\n谱师:P4koo(feat.rerone)",
            "37-5" to "曲名:妄想♡ちゅー!!\n谱师:Ponchi♪ feat.はぁち",
            "38-0" to "曲名:NO ONE YES MAN\n谱师:MYUKKE.",
            "38-1" to "曲名:雪降り、メリクリ （MD edit）\n谱师:A-39",
            "38-2" to "曲名:Igallta\n谱师:Se-U-Ra",
            "39-0" to "曲名:去剪海的日子\n谱师:砂糖协会",
            "39-1" to "曲名:happy hour\n谱师:砂糖协会",
            "39-2" to "曲名:世纪末的夏天\n谱师:天游 & 玛安娜",
            "39-3" to "曲名:twinkle night\n谱师:nyankobrq & yaca feat. somunia",
            "39-4" to "曲名:アルヤハレルヤ\n谱师:シオカラ（Haruka Toki + TEA）",
            "39-5" to "曲名:Blush (feat. MYLK)\n谱师:fusq",
            "39-6" to "曲名:裸のSummer\n谱师:オリーブがある",
            "39-7" to "曲名:BLESS ME(Samplingsource)\n谱师:オリーブがある",
            "39-8" to "曲名:FM 17314 SUGAR RADIO\n谱师:砂糖协会",
            "40-0" to "曲名:Rush-More\n谱师:litmus*",
            "40-1" to "曲名:Kill My Fortune\n谱师:アリスシャッハと魔法の楽団",
            "40-2" to "曲名:よさり 月蛍澄み昇りて\n谱师:かゆき feat.燈露",
            "40-3" to "曲名:JUMP! HardCandy\n谱师:Gəyxe/HJLL",
            "40-4" to "曲名:雲雀\n谱师:Street",
            "40-5" to "曲名:OCCHOCO-REST-LESS\n谱师:MYUKKE.",
            "41-0" to "曲名:超·东方不眠夜\n谱师:fizzd",
            "41-1" to "曲名:嗅炸弹的博美犬\n谱师:Stinkbug & Coda, Original song(Artificial Intelligence Bomb) from: naruto",
            "41-2" to "曲名:轮滑迪斯科震颤\n谱师:Vince Kaichan",
            "41-3" to "曲名:玫瑰花园\n谱师:RoDy.cOde",
            "41-4" to "曲名:EMOMOMO\n谱师:Ras",
            "41-5" to "曲名:赫拉克勒斯\n谱师:Yooh",
            "42-0" to "曲名:Bad Apple!! feat. Nomico\n谱师:Alstroemeria Records",
            "42-1" to "曲名:色は匂へど散りぬるを\n谱师:幽閉サテライト",
            "42-2" to "曲名:チルノのパーフェクトさんすう教室\n谱师:ARM＋夕野ヨシミ (IOSYS) feat. miko",
            "42-3" to "曲名:緋色月下、狂咲ノ絶\n谱师:EastNewSound",
            "42-4" to "曲名:花月夜\n谱师:Yonder Voice",
            "42-5" to "曲名:無意識レクイエム\n谱师:森羅万象",
            "43-0" to "曲名:The Happycore Idol\n谱师:3R2",
            "43-1" to "曲名:天津甕星\n谱师:削除",
            "43-2" to "曲名:ARIGA THESIS\n谱师:MYUKKE.",
            "43-3" to "曲名:ナイト・オブ・ナイツ\n谱师:ビートまりお",
            "43-4" to "曲名:#サイケデリック目黒川\n谱师:uma with モリモリあつし feat. ましろ",
            "43-5" to "曲名:can you feel it\n谱师:MK",
            "43-6" to "曲名:Midnight O'clock\n谱师:mossari feat.TEA",
            "43-7" to "曲名:Rin\n谱师:a_hisa",
            "43-8" to "曲名:Smile-mileS\n谱师:onoken",
            "43-9" to "曲名:信仰と存在\n谱师:他人事 feat. 否",
            "43-10" to "曲名:カタリスト\n谱师:P4koo(feat.つゆり花鈴)",
            "43-11" to "曲名:don't！stop！eroero！\n谱师:iKz feat.Bunny Girl Rin",
            "43-12" to "曲名:ぱぴぷぴぷぴぱ\n谱师:ころねぽち With 立秋",
            "43-13" to "曲名:Sand Maze\n谱师:a_hisa",
            "43-14" to "曲名:Diffraction\n谱师:StormWolf/Rakuda",
            "43-15" to "曲名:悪夢 / feat.つぐ\n谱师:吐息．",
            "43-16" to "曲名:Queen Aluett\n谱师:LeaF",
            "43-17" to "曲名:DROPS (feat. Such)\n谱师:Zekk, poplavor",
            "43-18" to "曲名:物凄い狂っとるフランちゃんが物凄いうた\n谱师:Halozy",
            "43-19" to "曲名:snooze\n谱师:wotaku feat. SHIKI",
            "43-20" to "曲名:Kuishinbo Hacker feat.Kuishinbo Akachan\n谱师:Neko Hacker",
            "43-21" to "曲名:犬之歌\n谱师:Aiobahn feat.Nanahira",
            "44-0" to "曲名:Party in the HOLLOWood feat. ななひら\n谱师:t+pazolite",
            "44-1" to "曲名:嘤嘤大作战\n谱师:iKz feat.祖娅纳惜",
            "44-2" to "曲名:Howlin' Pumpkin\n谱师:brz1128",
            "45-0" to "曲名:ONOMATO Pairing!!!\n谱师:t+pazolite feat. ななひら",
            "45-1" to "曲名:with U\n谱师:t+pazolite & Massive New Krew feat. リリィ(CV青木志貴)",
            "45-2" to "曲名:Chariot\n谱师:USAO",
            "45-3" to "曲名:GASHATT\n谱师:onoken",
            "45-4" to "曲名:LIN NE KRO NE feat. lasah\n谱师:sasakure.UK",
            "45-5" to "曲名:天使光輪\n谱师:REDALiCE & cosMo@暴走P",
            "46-0" to "曲名:Bang!!\n谱师:BOOGEY VOXX",
            "46-1" to "曲名:Paradise Ⅱ\n谱师:Sound Souler",
            "46-2" to "曲名:Symbol\n谱师:Silaver",
            "46-3" to "曲名:ネコジャラシ\n谱师:EmoCosine",
            "46-4" to "曲名:A Philosophical Wanderer\n谱师:tezuka x Yunosuke",
            "46-5" to "曲名:异想天\n谱师:Zris",
            "47-0" to "曲名:秋の陽炎\n谱师:karatoPαnchii feat.はるの",
            "47-1" to "曲名:GIMME DA BLOOD\n谱师:C-Show",
            "47-2" to "曲名:Libertas\n谱师:Zekk",
            "47-3" to "曲名:Cyaegha\n谱师:USAO",
            "48-0" to "曲名:glory day\n谱师:BEXTER x Mycin.T",
            "48-1" to "曲名:Bright Dream\n谱师:M2U",
            "48-2" to "曲名:Groovin Up\n谱师:Mycin.T",
            "48-3" to "曲名:I Want You\n谱师:Lin-G",
            "48-4" to "曲名:OBLIVION\n谱师:ESTi",
            "48-5" to "曲名:Elastic STAR\n谱师:Forte Escape",
            "48-6" to "曲名:U.A.D\n谱师:HAYAKO",
            "48-7" to "曲名:Jealousy\n谱师:3rd Coast",
            "48-8" to "曲名:Memory of Beach\n谱师:M2U",
            "48-9" to "曲名:Don't Die\n谱师:Paul Bazooka",
            "48-10" to "曲名:Y (CE Ver.)\n谱师:ND Lee",
            "48-11" to "曲名:Fancy Night\n谱师:SiNA x CHUCK",
            "48-12" to "曲名:Can We Talk\n谱师:Forte Escape",
            "48-13" to "曲名:Give Me 5\n谱师:ND Lee",
            "48-14" to "曲名:Nightmare\n谱师:M2U",
            "49-0" to "曲名:Pray a LOVE\n谱师:BOOGEY VOXX",
            "49-1" to "曲名:恋愛回避依存症\n谱师:Matthiola Records",
            "49-2" to "曲名:Daisuki Dayo feat.Wotoha\n谱师:Neko Hacker",
            "50-0" to "曲名:Nyan Cat\n谱师:daniwell",
            "50-1" to "曲名:ペロペロ in the Universe\n谱师:立秋 feat.ちょこ",
            "50-2" to "曲名:陰キャ陽キャ陰陽師\n谱师:ヒゲドライバー",
            "50-3" to "曲名:KABOOOOOM!!!!\n谱师:t+pazolite",
            "50-4" to "曲名:Doppelganger\n谱师:LeaF",
            "51-0" to "曲名:假面日记\n谱师:小野道ono feat.早木旋子",
            "51-1" to "曲名:Reminiscence\n谱师:technoplanet feat. 天輝おこめ (from KAWAII MUSIC)",
            "51-2" to "曲名:堕堕天使\n谱师:Project Mons feat.胡桃Usa",
            "51-3" to "曲名:D.I.Y.\n谱师:BOOGEY VOXX",
            "51-4" to "曲名:男子in☆バーチャランド\n谱师:IOSYS",
            "51-5" to "曲名:虁\n谱师:Apo11o program ft.ドーラ（にじさんじ）",
            "52-0" to "曲名:夜的搁浅\n谱师:砂糖协会",
            "52-1" to "曲名:daydream girl\n谱师:ANK/早木旋子",
            "52-2" to "曲名:Ornamentじゃない(Muse Dash Mix)\n谱师:オリーブがある with 砂糖协会",
            "52-3" to "曲名:Baby Pink (w/ YUC'e)\n谱师:Moe Shop",
            "52-4" to "曲名:ここにいる (I'm Here)\n谱师:Aiobahn",
            "53-0" to "曲名:On And On!!\n谱师:ETIA. feat. Jenga",
            "53-1" to "曲名:Trip!\n谱师:FOIV feat.Tomin Yukino",
            "53-2" to "曲名:ほしのおとしもの\n谱师:ああああ",
            "53-3" to "曲名:Plucky Race\n谱师:よみぃ",
            "53-4" to "曲名:Fantasia Sonata Destiny\n谱师:PYKAMIA",
            "53-5" to "曲名:Run through\n谱师:Ryo Arue",
            "54-0" to "曲名:White Canvas (feat. 藍月なくる)\n谱师:rejection",
            "54-1" to "曲名:Gloomy Flash (feat. Mami)\n谱师:Zekk & poplavor",
            "54-2" to "曲名:今月のおすすめプレイリストを検索します\n谱师:kazeoff",
            "54-3" to "曲名:Sunday Night (feat. Kanata.N)\n谱师:Mameyudoufu",
            "54-4" to "曲名:Goodbye Goodnight (feat. Shully)\n谱师:rejection",
            "54-5" to "曲名:ENDLESS CIDER (feat. Such)\n谱师:Mameyudoufu",
            "55-0" to "曲名:月に叢雲華に風\n谱师:幽閉サテライト",
            "55-1" to "曲名:パチュリーズ・ベストヒットGSK\n谱师:minami＋七条レタス(IOSYS) feat. 岩杉夏",
            "55-2" to "曲名:物凄いスペースシャトルでこいしが物凄いうた\n谱师:Halozy",
            "55-3" to "曲名:囲い無き世は一期の月影\n谱师:豚乙女",
            "55-4" to "曲名:サイケデリック鬼桜同盟\n谱师:SOUND HOLIC feat. Nana Takahashi",
            "55-5" to "曲名:悪戯センセーション\n谱师:森羅万象",
            "56-0" to "曲名:Psyched Fevereiro\n谱师:t+pazolite",
            "56-1" to "曲名:インフェルノシティ\n谱师:Ponchi♪ feat.はぁち",
            "56-2" to "曲名:Paradigm Shift\n谱师:モリモリあつし",
            "56-3" to "曲名:Snapdragon\n谱师:NeLiME",
            "56-4" to "曲名:Prestige and Vestige\n谱师:ikaruga_nex & 影虎。",
            "56-5" to "曲名:Tiny Fate\n谱师:Capchii",
            "57-0" to "曲名:ときめき★メテオストライク\n谱师:TRIFLATS",
            "57-1" to "曲名:Down Low\n谱师:James Landino ft. Raj Ramayya",
            "57-2" to "曲名:LOUDER MACHINE\n谱师:NOMA",
            "57-3" to "曲名:それはもうらぶちゅ\n谱师:タケノコ少年 feat. 周央サンゴ（にじさんじ）",
            "57-4" to "曲名:Rave_Tech\n谱师:3R2",
            "57-5" to "曲名:Brilliant & Shining! (Game Edit.)\n谱师:Halv",
            "58-0" to "曲名:People People\n谱师:Neko Hacker feat. Nanahira",
            "58-1" to "曲名:Endless Error Loop\n谱师:Neko Hacker feat. Nanahira",
            "58-2" to "曲名:Forbidden Pizza!\n谱师:Camellia feat. Nanahira",
            "58-3" to "曲名:ボーカルに無茶させんな\n谱师:t+pazolite feat. Nanahira"
        )
    )
}


