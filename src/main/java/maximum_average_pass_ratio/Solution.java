package maximum_average_pass_ratio;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        List<List<Integer>> copy = Arrays.stream(classes)
                .map(c -> Arrays.asList(c[0], c[1]))
                .collect(Collectors.toList());
        double extraRatio = 0;
        for (int i = 1; i <= extraStudents; i++) {
            double max = 0d;
            int indexOfMax = 0;
            for (int j = 0; j < copy.size(); j++) {
                double currentDiff = getDifferenceWithNext(copy.get(j).get(0), copy.get(j).get(1));
                if (currentDiff > max) {
                    max = currentDiff;
                    indexOfMax = j;
                }
            }
            copy.get(indexOfMax).set(0, copy.get(indexOfMax).get(0) + 1);
            copy.get(indexOfMax).set(1, copy.get(indexOfMax).get(1) + 1);
            extraRatio += max;
        }

//        System.out.println(copy);

        return (extraRatio + getClassesRatio(classes)) / classes.length;
    }

    private double getDifferenceWithNext(int dividend, int delimeter) {
        return (double) (delimeter - dividend) / (delimeter * (delimeter + 1));
    }

    private double getClassesRatio(int[][] classes) {
        return Arrays.stream(classes)
                .mapToDouble(c -> (double) c[0] / c[1])
//                .peek(System.out::println)
                .sum();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        long start = System.currentTimeMillis();
        // 0.6017073671169322
        System.out.println(solution.maxAverageRatio(new int[][]{
                        new int[]{494, 710},
                        new int[]{365, 473},
                        new int[]{354, 356},
                        new int[]{635, 994},
                        new int[]{528, 763},
                        new int[]{553, 944},
                        new int[]{223, 797},
                        new int[]{137, 557},
                        new int[]{248, 425},
                        new int[]{685, 820},
                        new int[]{65, 971},
                        new int[]{246, 264},
                        new int[]{307, 969},
                        new int[]{548, 923},
                        new int[]{475, 925},
                        new int[]{343, 645},
                        new int[]{454, 997},
                        new int[]{17, 504},
                        new int[]{500, 721},
                        new int[]{136, 972},
                        new int[]{28, 436},
                        new int[]{192, 397},
                        new int[]{293, 575},
                        new int[]{387, 706},
                        new int[]{196, 895},
                        new int[]{606, 689},
                        new int[]{409, 823},
                        new int[]{715, 759},
                        new int[]{27, 250},
                        new int[]{496, 659},
                        new int[]{349, 903},
                        new int[]{372, 456},
                        new int[]{81, 317},
                        new int[]{881, 977},
                        new int[]{60, 990},
                        new int[]{532, 966},
                        new int[]{162, 323},
                        new int[]{55, 140},
                        new int[]{371, 484},
                        new int[]{248, 875},
                        new int[]{625, 908},
                        new int[]{659, 942},
                        new int[]{44, 728},
                        new int[]{36, 846},
                        new int[]{15, 958},
                        new int[]{489, 997},
                        new int[]{470, 917},
                        new int[]{479, 838},
                        new int[]{440, 788},
                        new int[]{249, 416}, new int[]{107, 181}, new int[]{249, 292}, new int[]{236, 312},
                        new int[]{26, 109}, new int[]{211, 440}, new int[]{681, 944}, new int[]{293, 928},
                        new int[]{508, 773}, new int[]{288, 816}, new int[]{314, 966}, new int[]{113, 647},
                        new int[]{143, 478}, new int[]{288, 321}, new int[]{319, 807}, new int[]{602, 852},
                        new int[]{348, 553}, new int[]{37, 305}, new int[]{202, 262}, new int[]{476, 515},
                        new int[]{741, 983}, new int[]{544, 861}, new int[]{229, 773}, new int[]{4, 321},
                        new int[]{468, 957}, new int[]{67, 114}, new int[]{362, 437}, new int[]{44, 259},
                        new int[]{144, 710}, new int[]{666, 717}, new int[]{313, 517}, new int[]{384, 801},
                        new int[]{352, 409}, new int[]{184, 929}, new int[]{798, 886}, new int[]{54, 480},
                        new int[]{335, 861}, new int[]{168, 616}, new int[]{235, 835}, new int[]{167, 492},
                        new int[]{437, 637}, new int[]{408, 560}, new int[]{113, 320}, new int[]{780, 924},
                        new int[]{94, 628}, new int[]{471, 997}, new int[]{167, 885}, new int[]{604, 966},
                        new int[]{446, 503}, new int[]{144, 655}, new int[]{65, 760}, new int[]{32, 572},
                        new int[]{547, 717}, new int[]{491, 585}, new int[]{552, 615}, new int[]{136, 245},
                        new int[]{46, 921}, new int[]{551, 814}, new int[]{309, 424}, new int[]{701, 827},
                        new int[]{586, 993}, new int[]{899, 944}, new int[]{264, 475}, new int[]{358, 584},
                        new int[]{342, 566}, new int[]{375, 529}, new int[]{35, 581}, new int[]{185, 438},
                        new int[]{228, 509}, new int[]{836, 997}, new int[]{381, 647}, new int[]{260, 674},
                        new int[]{744, 986}, new int[]{761, 775}, new int[]{68, 249}, new int[]{266, 632},
                        new int[]{300, 867}, new int[]{526, 792}, new int[]{35, 301}, new int[]{490, 946},
                        new int[]{494, 724}, new int[]{168, 888}, new int[]{95, 868}, new int[]{602, 690},
                        new int[]{544, 932}, new int[]{204, 561}, new int[]{52, 586}, new int[]{536, 596}, new int[]{293, 348}, new int[]{68, 569}, new int[]{232, 618}, new int[]{607, 672}, new int[]{149, 174}, new int[]{698, 737}, new int[]{444, 647}, new int[]{628, 984}, new int[]{322, 649}, new int[]{727, 965}, new int[]{205, 621}, new int[]{797, 961}, new int[]{477, 683}, new int[]{943, 989}, new int[]{190, 906}, new int[]{1, 783}, new int[]{52, 208}, new int[]{18, 79}, new int[]{397, 804}, new int[]{371, 922}, new int[]{193, 448}, new int[]{860, 938}, new int[]{6, 166}, new int[]{437, 838}, new int[]{33, 252}, new int[]{64, 927}, new int[]{116, 403}, new int[]{379, 384}, new int[]{566, 864}, new int[]{616, 840}, new int[]{137, 876}, new int[]{390, 536}, new int[]{97, 655}, new int[]{326, 763}, new int[]{502, 648}, new int[]{4, 479}, new int[]{241, 414}, new int[]{82, 576}, new int[]{444, 869}, new int[]{604, 616}, new int[]{20, 386}, new int[]{353, 528}, new int[]{238, 301}, new int[]{408, 509}, new int[]{414, 984}, new int[]{52, 379}, new int[]{64, 863}, new int[]{418, 621}, new int[]{439, 602}, new int[]{161, 945}, new int[]{149, 708}, new int[]{196, 339}, new int[]{145, 504}, new int[]{424, 936}, new int[]{322, 773}, new int[]{7, 791}, new int[]{375, 907}, new int[]{382, 981}, new int[]{372, 748}, new int[]{83, 803}, new int[]{393, 770}, new int[]{135, 733}, new int[]{661, 972}, new int[]{38, 895}, new int[]{51, 145}, new int[]{317, 751}, new int[]{271, 341}, new int[]{42, 82}, new int[]{131, 141}, new int[]{246, 326}, new int[]{216, 987}, new int[]{162, 773}, new int[]{204, 455}, new int[]{382, 419}, new int[]{135, 319}, new int[]{608, 804}, new int[]{188, 472}, new int[]{26, 588}, new int[]{183, 752}, new int[]{642, 970}, new int[]{33, 125}, new int[]{145, 765}, new int[]{425, 676}, new int[]{299, 526}, new int[]{218, 773}, new int[]{59, 920}, new int[]{73, 618}, new int[]{538, 987}, new int[]{526, 795}, new int[]{554, 937}, new int[]{727, 956}, new int[]{435, 750}, new int[]{275, 710}, new int[]{482, 573}, new int[]{452, 477}, new int[]{594, 663}, new int[]{635, 927}, new int[]{84, 579}, new int[]{109, 352}, new int[]{308, 893}, new int[]{235, 799}, new int[]{46, 244}, new int[]{119, 211}, new int[]{184, 672}, new int[]{403, 771}, new int[]{772, 840}, new int[]{415, 912}, new int[]{132, 649}, new int[]{579, 847}, new int[]{237, 576}, new int[]{212, 658}, new int[]{500, 896}, new int[]{602, 707}, new int[]{658, 928}, new int[]{158, 703}, new int[]{759, 990}, new int[]{644, 776}, new int[]{120, 393}, new int[]{368, 456}, new int[]{22, 688}, new int[]{617, 980}, new int[]{493, 825}, new int[]{500, 653}, new int[]{61, 790}, new int[]{256, 625}, new int[]{291, 920}, new int[]{178, 519}, new int[]{481, 624}, new int[]{70, 768}, new int[]{326, 880}, new int[]{89, 984}, new int[]{268, 852}, new int[]{714, 832}, new int[]{310, 533}, new int[]{691, 870}, new int[]{133, 558}, new int[]{654, 955}, new int[]{61, 323}, new int[]{82, 151}, new int[]{513, 943}, new int[]{134, 717}, new int[]{177, 682}, new int[]{26, 794}, new int[]{548, 631}, new int[]{1, 63}, new int[]{71, 774}, new int[]{370, 553}, new int[]{489, 944}, new int[]{50, 703}, new int[]{538, 887}, new int[]{664, 689}, new int[]{80, 547}, new int[]{466, 471}, new int[]{581, 939}, new int[]{19, 689}, new int[]{283, 365}, new int[]{83, 460}, new int[]{66, 356}, new int[]{180, 182}, new int[]{588, 776}, new int[]{5, 591}, new int[]{199, 405}, new int[]{583, 686}, new int[]{230, 967}, new int[]{159, 499}, new int[]{126, 357}, new int[]{537, 879}, new int[]{690, 933}, new int[]{503, 844}, new int[]{106, 611}, new int[]{737, 885}, new int[]{554, 638}, new int[]{182, 697}, new int[]{513, 936}, new int[]{100, 317}, new int[]{308, 993}, new int[]{91, 923}, new int[]{636, 960}, new int[]{120, 586}, new int[]{330, 551}, new int[]{452, 590}, new int[]{595, 758}, new int[]{296, 451}, new int[]{296, 776}, new int[]{450, 519}, new int[]{805, 805}, new int[]{546, 933}, new int[]{473, 604}, new int[]{154, 312}, new int[]{277, 597}, new int[]{859, 937}, new int[]{80, 848}, new int[]{293, 684}, new int[]{35, 582}, new int[]{406, 891}, new int[]{134, 734}, new int[]{502, 597}, new int[]{670, 967}, new int[]{791, 841}, new int[]{748, 880}, new int[]{332, 735}, new int[]{269, 683}, new int[]{125, 979}, new int[]{297, 592}, new int[]{228, 447}, new int[]{250, 400}, new int[]{554, 599}, new int[]{75, 412}, new int[]{130, 691}, new int[]{44, 260}, new int[]{260, 699}, new int[]{241, 787}, new int[]{83, 348}, new int[]{265, 940}, new int[]{107, 391}, new int[]{135, 248}, new int[]{498, 995}, new int[]{332, 474}, new int[]{417, 795}, new int[]{84, 523}, new int[]{480, 597}, new int[]{37, 999}, new int[]{204, 445}, new int[]{404, 580}, new int[]{585, 697}, new int[]{267, 790}, new int[]{632, 633}, new int[]{132, 776}, new int[]{137, 879}, new int[]{387, 868}, new int[]{19, 57}, new int[]{659, 985}, new int[]{55, 859}, new int[]{4, 452}, new int[]{355, 528}, new int[]{457, 839}, new int[]{335, 997}, new int[]{191, 823}, new int[]{682, 716}, new int[]{215, 984}, new int[]{452, 744}, new int[]{220, 638}, new int[]{205, 678}, new int[]{399, 942}, new int[]{340, 793}, new int[]{726, 969}, new int[]{286, 454}, new int[]{166, 281}, new int[]{308, 687}, new int[]{90, 785}, new int[]{623, 637}, new int[]{73, 561}, new int[]{542, 634}, new int[]{492, 585}, new int[]{558, 608}, new int[]{250, 982}, new int[]{98, 512}, new int[]{123, 461}, new int[]{518, 559}, new int[]{116, 171}, new int[]{714, 994}, new int[]{333, 661}, new int[]{535, 732}, new int[]{387, 801}, new int[]{230, 596}, new int[]{99, 534}, new int[]{462, 804}, new int[]{115, 235}, new int[]{784, 864}, new int[]{90, 488}, new int[]{258, 591}, new int[]{527, 962}, new int[]{310, 841}, new int[]{21, 342}, new int[]{619, 825}, new int[]{698, 816}, new int[]{250, 394}, new int[]{157, 442}, new int[]{514, 580}, new int[]{70, 460}, new int[]{488, 906}, new int[]{263, 420}, new int[]{757, 858}, new int[]{554, 806}, new int[]{570, 591}, new int[]{70, 722}, new int[]{126, 930}, new int[]{394, 957}, new int[]{122, 859}, new int[]{274, 388}, new int[]{33, 873}, new int[]{95, 558}, new int[]{77, 353}, new int[]{402, 1000}, new int[]{482, 692}, new int[]{491, 960}, new int[]{687, 765}, new int[]{661, 962}, new int[]{161, 657}, new int[]{44, 410}, new int[]{268, 408}, new int[]{402, 692}, new int[]{395, 464}, new int[]{16, 731}, new int[]{249, 693}, new int[]{134, 551}, new int[]{76, 511}, new int[]{215, 414}, new int[]{110, 861}, new int[]{706, 791}, new int[]{934, 966}, new int[]{735, 979}, new int[]{202, 473}, new int[]{414, 651}, new int[]{416, 446}, new int[]{27, 820}, new int[]{23, 288}, new int[]{45, 795}, new int[]{499, 759}, new int[]{134, 429}, new int[]{610, 796}, new int[]{181, 762}, new int[]{194, 517}, new int[]{386, 846}, new int[]{291, 903}, new int[]{954, 987}, new int[]{82, 977}, new int[]{44, 561}, new int[]{424, 728}, new int[]{150, 479}, new int[]{163, 305}, new int[]{41, 666}, new int[]{308, 600}, new int[]{33, 300}, new int[]{537, 630}, new int[]{301, 336}, new int[]{33, 500}, new int[]{89, 960}, new int[]{168, 759}, new int[]{649, 798}, new int[]{694, 771}, new int[]{393, 610}, new int[]{720, 954}, new int[]{505, 646}, new int[]{271, 835}, new int[]{460, 801}, new int[]{24, 431}, new int[]{36, 303}, new int[]{792, 903}, new int[]{51, 356}, new int[]{205, 351}, new int[]{186, 251}, new int[]{142, 967}, new int[]{94, 857}, new int[]{42, 97}, new int[]{159, 858}, new int[]{315, 324}, new int[]{505, 607}, new int[]{588, 614}, new int[]{22, 486}, new int[]{395, 592}, new int[]{389, 558}, new int[]{241, 754}, new int[]{146, 539}, new int[]{564, 788}, new int[]{108, 939}, new int[]{428, 567}, new int[]{230, 396}, new int[]{571, 983}, new int[]{9, 611}, new int[]{848, 876}, new int[]{576, 586}, new int[]{324, 986}, new int[]{420, 813}, new int[]{398, 905}, new int[]{572, 893}, new int[]{645, 947}, new int[]{224, 682}, new int[]{136, 448}, new int[]{181, 839}, new int[]{113, 582}, new int[]{116, 739}, new int[]{306, 834}},
                61506));

        System.out.println(System.currentTimeMillis() - start);
    }
}
