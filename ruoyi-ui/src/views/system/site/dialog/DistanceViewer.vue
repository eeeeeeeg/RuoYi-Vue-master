<!-- LocationViewer.vue -->
<template>
  <div style="position: relative">
    <!-- 目标经纬度 lat : 31.272655 lng : 121.687986 -->
    <label>地址：</label><el-input v-model="keyword" clearable />
    <div style="position: relative; z-index: 1001">
      <baidu-map
        @ready="loadedMap"
        class="map"
        :center="location"
        :zoom="zoom"
        :scroll-wheel-zoom="true"
        style="height: 400px"
        ref="baiduRef"
        @click="handleMapClick"
        @blur="
          () => {
            keyword = '';
          }
        "
      >
        <bm-local-search
          :keyword="keyword"
          :auto-viewport="true"
          :location="location"
          @markersset="handleClick"
        ></bm-local-search>
        <!-- 
        <bm-marker :position="location" v-if="location">
          <bm-label content="当前位置" />
        </bm-marker> -->
        <bm-marker :position="clickedPosition" v-if="clickedPosition">
          <bm-label content="站点位置" />
        </bm-marker>
      </baidu-map>
    </div>
    <div v-if="clickedPosition">
      <div>选中站点数据</div>
      中文地址： {{ targetPointChinese }}
      <div>
        纬度：{{ clickedPosition ? clickedPosition.lat : "" }}, 经度：{{
          clickedPosition ? clickedPosition.lng : ""
        }}
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "LocationViewer",
  data() {
    return {
      keyword: "",
      location: { lat: 32.514746, lng: 120.144894 },
      zoom: 1,
      clickedPosition: null,
      gc: null,
      BMap: null,
      distance: null,
      chineseAddress: null,
      initFlag: false,
      targetPointChinese: null,
    };
  },
  methods: {
    reverseGeocode({ lng, lat }) {
      var map = new this.BMap.Map("container", { enableMapClick: false });
      console.log("wangc - map : ", map);
      const point = new this.BMap.Point(lng, lat);
      // map.setCenter(point);
      this.gc.getLocation(point, (result) => {
        if (result) {
          this.chineseAddress = result.address;
        } else {
          this.chineseAddress = "未找到地址";
        }
      });
    },
    async transferFunc() {
      //坐标转换完之后的回调函数
      const that = this;
      var convertor = new this.BMap.Convertor();
      var pointArr = [];
      pointArr.push(this.location);
      await convertor.translate(pointArr, 1, 5, function (data) {
        if (data.status === 0) {
          that.location = data.points[0];
        }
      });
    },
    calculateDistance(point1, point2) {
      if (!(point1 && point2)) {
        return;
      }
      const map = new this.BMap.Map();
      const distance = map.getDistance(
        new this.BMap.Point(point1.lng, point1.lat),
        new this.BMap.Point(point2.lng, point2.lat)
      );
      this.distance = distance.toFixed(2); // 保留两位小数
    },
    loadedMap({ BMap, map }) {
      this.gc = new BMap.Geocoder();
      //创建地址解析器实例
      this.BMap = BMap;
      this.initFlag = true;
      const geolocation = new BMap.Geolocation();
      // 开启定位
      var that = this;
      console.log("wangc -------------- this.location:", this.location);
      // // 创建地图实例
      map.setCenter({ lat: 32.514746, lng: 120.144894 });
      geolocation.getCurrentPosition(function (result) {
        if (this.getStatus() == 0) {
          // 定位成功，result.point 是定位坐标
          const point = result.point;
          that.location = point;
          that.reverseGeocode(that.location);
          console.log("当前位置:", point.lng, point.lat);
        } else {
          // 定位失败
          console.error("定位失败:", this.getStatus());
        }
      });
    },
    handleMapClick(event) {
      // 获取点击位置的经纬度坐标
      const clickedLng = event.point.lng;
      const clickedLat = event.point.lat;

      // 更新点击位置
      this.clickedPosition = {
        lng: clickedLng,
        lat: clickedLat,
      };
      const point = new this.BMap.Point(clickedLng, clickedLat);
      this.gc.getLocation(point, (result) => {
        this.keyword = "";
        if (result) {
          this.targetPointChinese = result.address;
          this.$emit("updateSiteInfo", {
            name: this.targetPointChinese,
            coordinates: `(${clickedLng}, ${clickedLat})`,
          });
        } else {
          this.targetPointChinese = "未找到地址";
        }
      });
    },
    // //设置远端的坐标
    // setRemoteLocation({ lng, lat }) {
    //   this.$nextTick(() => {
    //     this.location = {
    //       lng: lng.trim(),
    //       lat: lat.trim(),
    //     };
    //   });
    //   console.log("wangc ---------- setLocation:", this.location);
    // },
    handleClick(event) {
      console.log("handleClick", event);
    },
  },
};
</script>

<style>
.map {
  width: 100%;
  height: 200px;
}
</style>
