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
<style src='./home.css'></style>
