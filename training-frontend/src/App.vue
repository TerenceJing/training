<template >
  <div id="app1">
    <header id="header">Vue.js</header>
    <main id="main">
      <div id="aside">
         <router-link :to="{path:'/hello'}" title="router-link方式跳转">基 础 知 识</router-link>
        <br />
        <el-button
          type="text"
          class="href-container"
          @click="go2Style"
          title="this.$router.push方式跳转"
        >动 态 样 式</el-button>
        <br />
        <el-button
          type="text"
          class="href-container"
          @click="go2Lifecycle"
          title="this.$router.push方式跳转"
        >生 命 周 期</el-button>
        <br />
        <el-button
          type="text"
          class="href-container"
          @click="go2Component"
          title="this.$router.push方式跳转"
        >组 件 封 装</el-button>
      </div>
      <div id="main-container">
        <router-view />
        <!-- <el-scrollbar>
          <div v-for="item in 70" :key="item" >{{item}}</div>
        </el-scrollbar> -->
      </div>
    </main>
    <footer id="footer">
      All rights reserved @Terence
    </footer>
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
#header {
  /* border:1px solid red; */
  background-color: rgb(52, 58, 70);
  line-height: 50px;
  padding-left: 8%;
  color: papayawhip;
  font-size: 18px;
}
#main{
  position: fixed;
  top:50px;
  bottom:50px;
  width:100%;
  /* border:1px solid rgb(209, 29, 29); */
}
#aside {
  position: fixed;
  background-color: rgb(68, 133, 151);
  top:50px;
  bottom:50px;
  text-align: center;
  /* border:3px solid rgb(29, 209, 140); */
  width:160px;
  font-size: 16px;
  border-radius: 0 3px  3px 0;
}
#main-container{
  position: fixed;
  left:170px;
  right:10px;
  top:60px;
  bottom:60px;
  border:1px solid rgb(129, 123, 123);
  border-radius: 3px;
  /* margin-top:10px; */
  /* margin-left:200px; */

}
#footer{
  position: fixed;
  bottom:0px;
  height:50px;
  width:100%;
  background-color: rgb(52, 58, 70);
  color:white;
  font-size:12px;
  text-align: center;
  line-height: 50px;
}
.href-container {
  color: rgb(52, 58, 70);
  font-size: 16px;
}

.zy-main {
  height: 100%;
  overflow: hidden;
}
.el-scrollbar{
  height: 100%;
}
.el-scrollbar__wrap{
  overflow: scroll;
  overflow-x:hidden;
   overflow-x:auto;
  overflow-y: auto;
}
</style>
