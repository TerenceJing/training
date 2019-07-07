<template >
  <div id="app1">
  <el-header class="vue-header">Vue.js</el-header>
  <el-container>
    <el-aside class="aside-container" >
      <router-link :to="{path:'/hello'}" title="router-link方式跳转">
      基 础 知 识
      </router-link>
      <br/>
      <el-button type="text" class="href-container" @click="go2Style" 
      title="this.$router.push方式跳转">
        动 态 样 式
      </el-button>
      <br/>
      <el-button type="text" class="href-container" @click="go2Lifecycle" 
      title="this.$router.push方式跳转">
        生 命 周 期
      </el-button>
      <br/>
      <el-button type="text" class="href-container" @click="go2Component" 
      title="this.$router.push方式跳转">
        组 件 封 装
      </el-button>
    </el-aside>
    <el-main><router-view/></el-main>
  </el-container>
  </div>
</template>

<script>
export default {
  name: 'App',
  data() {
    return {}
  },
  methods: {
    go2Base: function() {
      this.$router.push({ path: '/hello' })
    },
    go2Style() {
      this.$router.push({ path: '/style' })
    },
    go2Lifecycle() {
      this.$router.push({ path: '/lifecycle' })
    },
    go2Component() {
      this.$router.push({ path: '/component' })
    }
  }
}
</script>

<style>
body {
  margin: 0;
  padding: 0;
  color: rgb(52, 58, 70);
}
.vue-header {
  /* border:1px solid red; */
  background-color: rgb(52, 58, 70);
  line-height: 65px;
  padding-left: 8%;
  color: papayawhip;
  font-size: 25px;
}
.aside-container {
  /* border:1px solid red; */
  background-color: rgb(211, 220, 230);
  /* display: block; */
  text-align: center;
  height: 600px;
  padding: 10px 30px 10px;
  font-size:16px;
}
.href-container {
  color: rgb(52, 58, 70);
  font-size:16px;
}
</style>
