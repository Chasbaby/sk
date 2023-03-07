<template>
  <div id="Bulletins">
    <TitleSwiper></TitleSwiper>
    <div class="main">
      <el-container>
        <el-header>
          <ComponentTitle>
            <template v-slot:Chinese>公告</template>
            <template v-slot:English>Bulletins</template>
          </ComponentTitle>
        </el-header>
        <el-main>
          <!-- <ViewTemplate
            :listInfo="ViewTemplateListInfo"
            :type="type"
          ></ViewTemplate> -->
          <CategoryLink :listInfo="CategoryLinkInfo"></CategoryLink>
        </el-main>
      </el-container>
    </div>
  </div>
</template>

<script>
import ComponentTitle from "../components/ComponentsTitle";
import TitleSwiper from "../components/TitleSwiper";
import CategoryLink from "../components/CategoryLink";
import ViewTemplate from "../components/ViewTemplate";

export default {
  name: "Bulletins",
  data() {
    return {
      ViewTemplateListInfo: [
        {
          id: "001",
          src: "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
          title: "清华大学学位评定委员会2022年第四次全体会议举行",
          text: "8月14日下午，清华大学副校长曾嵘带队拜访密云区委书记余卫国，区委副书记、区长马新明，并实地考察调研空地一体环境感知与智能响应交叉研究平台建设情况。双方就国家重点实验室建设、空地一体环境感知与智能响应校学位评定委员会秘书长、研究生院副院长吴烨汇报了自主审核增列博士硕士学位授权点总体情况，临床医学院院长董家鸿、万科公共卫生与健康学院常务副院长梁万年和苏世民书院院长薛澜分别介绍了三个拟新增学位授权点具体建设情况。经表决，同意增列“临床医学”一级学科博士学位授权点、“公共卫生与健康”一级学科博士硕士学位授权点和“全球领导力”硕士专业学位授权点。",
          time: "2022.8.28",
        },
        {
          id: "002",
          src: "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
          title: "清华大学学位评定委员会2022年第四次全体会议举行",
          text: "8月14日下午，清华大学副校长曾嵘带队拜访密云区委书记余卫国，区委副书记、区长马新明，并实地考察调研空地一体环境感知与智能响应交叉研究平台建设情况。双方就国家重点实验室建设、空地一体环境感知与智能响应校学位评定委员会秘书长、研究生院副院长吴烨汇报了自主审核增列博士硕士学位授权点总体情况，临床医学院院长董家鸿、万科公共卫生与健康学院常务副院长梁万年和苏世民书院院长薛澜分别介绍了三个拟新增学位授权点具体建设情况。经表决，同意增列“临床医学”一级学科博士学位授权点、“公共卫生与健康”一级学科博士硕士学位授权点和“全球领导力”硕士专业学位授权点。",
          time: "2022.8.28",
        },
        {
          id: "003",
          src: "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
          title: "清华大学学位评定委员会2022年第四次全体会议举行",
          text: "8月14日下午，清华大学副校长曾嵘带队拜访密云区委书记余卫国，区委副书记、区长马新明，并实地考察调研空地一体环境感知与智能响应交叉研究平台建设情况。双方就国家重点实验室建设、空地一体环境感知与智能响应校学位评定委员会秘书长、研究生院副院长吴烨汇报了自主审核增列博士硕士学位授权点总体情况，临床医学院院长董家鸿、万科公共卫生与健康学院常务副院长梁万年和苏世民书院院长薛澜分别介绍了三个拟新增学位授权点具体建设情况。经表决，同意增列“临床医学”一级学科博士学位授权点、“公共卫生与健康”一级学科博士硕士学位授权点和“全球领导力”硕士专业学位授权点。",
          time: "2022.8.28",
        },
        {
          id: "004",
          src: "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
          title: "清华大学学位评定委员会2022年第四次全体会议举行",
          text: "8月14日下午，清华大学副校长曾嵘带队拜访密云区委书记余卫国，区委副书记、区长马新明，并实地考察调研空地一体环境感知与智能响应交叉研究平台建设情况。双方就国家重点实验室建设、空地一体环境感知与智能响应校学位评定委员会秘书长、研究生院副院长吴烨汇报了自主审核增列博士硕士学位授权点总体情况，临床医学院院长董家鸿、万科公共卫生与健康学院常务副院长梁万年和苏世民书院院长薛澜分别介绍了三个拟新增学位授权点具体建设情况。经表决，同意增列“临床医学”一级学科博士学位授权点、“公共卫生与健康”一级学科博士硕士学位授权点和“全球领导力”硕士专业学位授权点。",
          time: "2022.8.28",
        },
        {
          id: "005",
          src: "https://cube.elemecdn.com/6/94/4d3ea53c084bad6931a56d5158a48jpeg.jpeg",
          title: "清华大学学位评定委员会2022年第四次全体会议举行",
          text: "8月14日下午，清华大学副校长曾嵘带队拜访密云区委书记余卫国，区委副书记、区长马新明，并实地考察调研空地一体环境感知与智能响应交叉研究平台建设情况。双方就国家重点实验室建设、空地一体环境感知与智能响应校学位评定委员会秘书长、研究生院副院长吴烨汇报了自主审核增列博士硕士学位授权点总体情况，临床医学院院长董家鸿、万科公共卫生与健康学院常务副院长梁万年和苏世民书院院长薛澜分别介绍了三个拟新增学位授权点具体建设情况。经表决，同意增列“临床医学”一级学科博士学位授权点、“公共卫生与健康”一级学科博士硕士学位授权点和“全球领导力”硕士专业学位授权点。",
          time: "2022.8.28",
        },
      ],
      CategoryLinkInfo: [
        {
          id: "001",
          name: "景区通告",
          itemInfoList: [
            {
              id: "001",
              time: "2022.8.26",
              title: "​清华大学台湾研究院主办“大变局下的台湾问题”研讨会",
            },
            {
              id: "002",
              time: "2022.8.26",
              title: "​清华大学台湾研究院主办“大变局下的台湾问题”研讨会",
            },
            {
              id: "003",
              time: "2022.8.26",
              title: "​清华大学台湾研究院主办“大变局下的台湾问题”研讨会",
            },
          ],
        },
        {
          id: "002",
          name: "管理通知",
          itemInfoList: [
            {
              id: "001",
              time: "2022.8.26",
              title: "​清华大学台湾研究院主办“大变局下的台湾问题”研讨会",
            },
            {
              id: "002",
              time: "2022.8.26",
              title: "​清华大学台湾研究院主办“大变局下的台湾问题”研讨会",
            },
            {
              id: "003",
              time: "2022.8.26",
              title: "​清华大学台湾研究院主办“大变局下的台湾问题”研讨会",
            },
          ],
        },
        {
          id: "003",
          name: "优惠折扣",
          itemInfoList: [
            {
              id: "001",
              time: "2022.8.26",
              title: "​清华大学台湾研究院主办“大变局下的台湾问题”研讨会",
            },
            {
              id: "002",
              time: "2022.8.26",
              title: "​清华大学台湾研究院主办“大变局下的台湾问题”研讨会",
            },
            {
              id: "003",
              time: "2022.8.26",
              title: "​清华大学台湾研究院主办“大变局下的台湾问题”研讨会",
            },
          ],
        },
      ],
      type: "Bulletins",
    };
  },
  components: {
    ComponentTitle,
    TitleSwiper,
    CategoryLink,
    ViewTemplate,
  },
};
</script>
<style scoped>
#Bulletins {
  height: 0px;
}
#Bulletins .main {
  width: 80%;
  margin: 0 auto;
}
</style>
