<template>
  <div>
    <div class="static">isActive:{{isActive}}</div>
    <div :class="classObject">switch style</div>
    <br />
    <div
      v-bind:style="{ color: activeColor, fontSize: fontSize + 'px' }"
    >内联样式style绑定---测试1:&nbsp;内联样式直接绑定给style</div>
    <br />
    <div v-bind:style="styleObject">内联样式style绑定---测试2:&nbsp;样式变量绑定到html元素</div>
    <br />
    <div v-bind:style="[styleObject,styleObject2]">内联样式style绑定---测试3:&nbsp;数组形式将多个样式变量绑定到同一元素</div>
  </div>
</template>
<script src='./style.js'></script>
<style>
.static {
  border: 1px solid red;
}
</style>
