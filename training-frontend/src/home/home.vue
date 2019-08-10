<template>
  <div id="home">
    <header id="header">Training-frontend</header>
    <main id="main">
      <div id="left">
        <el-menu class="el-menu-vertical-demo" @select="handleSelect">
          <el-submenu v-for="(item,index) in menuList" :key="index" :index="item.index">
            <template slot="title">
              <i :class="item.icon" v-if="item.icon" ></i>
              <i v-if="!item.icon" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</i>
              <span slot="title">{{item.name}}</span>
              </template>
                <el-menu-item v-for="(child,id) in item.children" :key="id" :index="item.index+'.'+child.index">{{child.name}}</el-menu-item>
          </el-submenu>
        </el-menu>
      </div>
      <div id="navigator">{{menuName}}&nbsp;>&nbsp;{{detailName}}</div>
      <div id="main-container">
        <router-view />
      </div>
    </main>
    <footer id="footer">
      All rights reserved @Terence
    </footer>
  </div>
</template>
<script src='./home.js'></script>
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
  color: white;
  font-size: 18px;
}

#main {
  position: fixed;
  top: 50px;
  bottom: 50px;
  width: 100%;
  /* border:1px solid rgb(209, 29, 29); */
}

#left {
  position: fixed;
  background-color: rgb(68, 133, 151);
  top: 50px;
  bottom: 50px;
  text-align: center;
  /* border:3px solid rgb(29, 209, 140); */
  width: 160px;
  font-size: 16px;
  border-radius: 0 3px 3px 0;
}
#navigator {
  position: fixed;
  left: 167px;
  right: 7px;
  top: 60px;
  /* border: 1px solid rgb(129, 123, 123); */
}
#main-container {
  position: fixed;
  left: 167px;
  right: 7px;
  top: 85px;
  bottom: 57px;
  border: 1px solid rgb(129, 123, 123);
  border-radius: 3px;
  font-size: 13px;
  /* margin-top:10px; */
  /* margin-left:200px; */
}

#footer {
  position: fixed;
  bottom: 0px;
  height: 50px;
  width: 100%;
  background-color: rgb(52, 58, 70);
  color: white;
  font-size: 12px;
  text-align: center;
  line-height: 50px;
}

.href-container {
  color: rgb(52, 58, 70);
  font-size: 16px;
}

/* 滚动条 */
.zy-main {
  height: 100%;
  overflow: hidden;
}

.el-scrollbar {
  height: 100%;
}

.el-scrollbar__wrap {
  overflow: scroll;
  /* overflow-x: hidden; */
  /* x,y轴自动滚动条 */
  overflow-x: auto;
  overflow-y: auto;
}
/* 菜单栏 */
.el-menu {
  background-color: rgb(68, 133, 151);
  border-right: rgb(68, 133, 151);
  color: white;
  text-align: center;
}

.el-submenu__title {
  padding-left: 0px;
  color: rgb(241, 235, 235);
  text-align: left;
  font-size: 14px;
}
.el-icon-arrow-down:before {
  color: rgb(241, 235, 235);
}
.el-icon-location:before {
  color: rgb(241, 235, 235);
}
.el-submenu .el-menu-item {
  color: rgb(241, 235, 235);
  height: 40px;
  line-height: 30px;
  text-align: right;
  width: 80px;
  padding-left: 30px !important;
  background: rgb(93, 177, 201);
  margin-left: -40px;
}
.el-submenu__title:hover {
  background: rgb(42, 86, 99);
}
.el-submenu__title:visited {
  background: rgb(42, 86, 99);
}
.el-menu-item:hover {
  background: rgb(71, 143, 163);
}
.el-menu-item:visited {
  background: rgb(71, 143, 163);
  color: rgb(52, 58, 70);
}
</style>

