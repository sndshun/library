<template>
  <home-menu/>
  <div class="hidden-md-and-up">
    <swiper
        :modules="modules"
        :loop="true"
        :autoplay="{
           delay: 2500,
           disableOnInteraction: false,
         }"
    >
      <swiper-slide v-for="(item,index) in bannerList" :key="index">
        <el-image :src="item.imgUrl" fit="scale-down" :alt="item.imgDesc"/>
      </swiper-slide>
    </swiper>
    <br><br><br>
    <el-row justify="center">
      <el-col :span="22">
        <div class="title">关于</div>
        <div class="subtitle">xx图书管理系统</div>
        <div class="text">
          <p>
            图书馆是个收藏资讯、原始资料、资料库并提供相关服务的地方，可以由公共团体、政府机构或者私人组织开办。
            图书馆在人类文明发展及历史存留具显著作用，是人类智慧的宝库。
            在传统意义上，图书馆是收藏书和搜集各种出版物的地方。
          </p>
        </div>
      </el-col>
      <el-col :span="22">
        <br><br>
        <div class="title"></div>
        <div class="subtitle">历史</div>
        <div class="text">
          <p>
            早在公元前3000年时，巴比伦的神庙中就收藏有刻在胶泥板上的各类记载。
            最早的藏书地点是希腊神庙的藏书之所和附属于希腊哲学书院（公元前4世纪）的藏书之所。
            最早的图书馆——根据考古学家的发掘成果，
            我们已知世界上最早的图书馆是美索不达米亚平原的尼尼微图书馆
            （亚述帝国的国王亚述巴尼拔建立，所以又称亚述巴尼拔图书馆）。
          </p>
        </div>
      </el-col>
    </el-row>


  </div>
  <div class="hidden-sm-and-down">
    <swiper
        :style="{
      'height': '100vh',
      '--swiper-navigation-color': '#fff',
      '--swiper-pagination-color': '#fff',
    }"
        :speed="600"
        :parallax="true"
        :modules="modules2"
        class="mySwiper"
    >
      <div
          slot="container-start"
          class="parallax-bg"
          :style="{
        'background-image':
          'url('+banner+')',
      }"
          data-swiper-parallax="-23%"
      ></div>
      <swiper-slide class="swiper-item">
        <div class="title" data-swiper-parallax="-300">起源</div>
        <div class="subtitle" data-swiper-parallax="-200">过去</div>
        <div class="text" data-swiper-parallax="-100">
          <p>
            早在公元前3000年时，巴比伦的神庙中就收藏有刻在胶泥板上的各类记载。
            最早的藏书地点是希腊神庙的藏书之所和附属于希腊哲学书院（公元前4世纪）的藏书之所。
            最早的图书馆——根据考古学家的发掘成果，我们已知世界上最早的图书馆
            是美索不达米亚平原的尼尼微图书馆（亚述帝国的国王亚述巴尼拔建立，所以又称亚
            述巴尼拔图书馆）。这是现今已发掘的古文明遗址中保存最完整、规模最宏大、书籍最齐全的图
            书馆。在时间上要比埃及著名的亚历山大图书馆（古代最大图书馆）早400多年，
            而且由于泥版图书的特殊性，没有像亚历山大图书馆一样毁于战火，大部分都保存了下来。
          </p>
        </div>
      </swiper-slide
      >
      <swiper-slide class="swiper-item">
        <div class="title" data-swiper-parallax="-300">近代</div>
        <div class="subtitle" data-swiper-parallax="-200">现在</div>
        <div class="text" data-swiper-parallax="-100">
          <p>
            图书馆指搜集、整理及保存图书资讯， 以服务公众或特定对象之设施， 旨在建立及维护馆藏，
            以满足其资讯、研究、教育、文化或休闲的需求[1]。图书馆是个收藏资讯、原始资料、资料库并提供相关服务的地方，
            可以由公共团体、政府机构或者私人组织开办。图书馆在人类文明发展及历史存留具显著作用，是人类智慧的宝库。
            在传统意义上，图书馆是收藏书和搜集各种出版物的地方。然而，现在资料保存已经不止是保存图书，
            许多图书馆把地图、印刷物，或者其他档案和艺术作品保存在各种载体上，比如微缩胶片、磁带、CD、LP、
            盒式磁带、录像带和DVD。图书馆通过访问CD-ROM、订购数据库和互联网提供服务。因此，人们渐渐把现代图
            书馆重新定义为能够无限制得获取多种来源、多种格式的信息。除了提供资源，图书馆还有专家和图书馆员来提供服务，
            他们善于寻找和组织信息，并解释信息需求。近些年，人们对图书馆的理解已经超越了建筑的围墙，读者可以用电子工
            具例如电子书获得资源，图书馆员用各种数字工具来引导读者和分析海量知识
            。图书馆的另一作用是收藏人们不愿意购买（或者无力购买）的资源，作为馆藏提供大众使用或查阅。
          </p>
        </div>
      </swiper-slide
      >
      <swiper-slide class="swiper-item">
        <div class="title" data-swiper-parallax="-300">以后</div>
        <div class="subtitle" data-swiper-parallax="-200">未来</div>
        <div class="text" data-swiper-parallax="-100">
          <p>
            图书馆管理系统，能进行图书馆管理系统能实测国民经济和企业的各种运行情况；利用过去的数据预测未来；
            从企业全局出发辅助企业进行管理决策；利用信息控制企业的行为；帮助企业实现其规划目标。
            图书馆管理系统合运用了管理科学，系统科学，运筹学，统计学，计算机科学等学科的知识。
            可以通俗的简化的描述图书馆管理系统的三要素：系统的观点、数学的方法以及计算机的应用。
            图书馆管理系统概念结构主要由四大部分组成即信息源、信息处理器、信息用户、信息管理者组成。
          </p>
        </div>
      </swiper-slide>
    </swiper>
  </div>
</template>

<script setup>
import HomeMenu from '../components/LibraryHeader.vue'
import {onBeforeMount, ref} from "vue";
import {getBannerList} from "../api/attachment";
import {Swiper, SwiperSlide} from "swiper/vue";
import {Autoplay, Parallax} from "swiper";

// Import Swiper styles
import "swiper/css";

const modules = ref([Autoplay])
const modules2 = ref([Parallax])

const banner = ref()
const bannerList = ref([])
onBeforeMount(() => {
  getBannerList().then(res => {
    bannerList.value = res.data
    banner.value = res.data[0].imgUrl
  })
})

</script>

<style scoped>
.small {
  width: 100%;
  height: 100%;
}

.title {
  font-size: 41px;
  font-weight: 300;
}

.subtitle {
  font-size: 21px;
}

.text {
  font-size: 14px;
  max-width: 400px;
  line-height: 1.3;
}

.parallax-bg {
  position: absolute;
  left: 0;
  top: 0;
  width: 130%;
  height: 100%;
  -webkit-background-size: cover;
  background-size: cover;
  background-position: center;
}

.swiper-item {
  font-size: 18px;
  color: #fff;
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
  padding: 40px 60px;
}
</style>