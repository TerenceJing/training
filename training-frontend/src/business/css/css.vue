<template>
  <div id="css">
    <div class="test-btn">CSS变量测试 (全局变量、局部变量)</div>
    <el-button class="calc">CSS计算测试 calc()</el-button>
  </div>
</template>
<script></script>
<style scoped>
#css {
  --font_size: 30px;
}
.test-btn {
  /* 使用home.css中的root中的全局变量 */
  color: var(--base_color);
  background: var(--bg_color);
  /* 使用本页面的局部变量 --font_size*/
  font-size: var(--font_size);
}
.calc {
  margin-top: 10px;
  width: calc(100% - 500px);
}

/* CSS calc() 函数 ：calc() 函数用于动态计算长度值。 支持 + - * / 运算*/
</style>
