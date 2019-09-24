<template>
  <div>
    <div style="height:40px;line-height:40px;padding-left:40px;">级联下拉</div>
    <div style="padding-left:40px;">
      <!-- <el-cascader clearable :options="options" v-model="value" ></el-cascader> -->
      <el-cascader clearable filterable v-model="value" :props="props"></el-cascader>
    </div>
  </div>
</template>
<script src='./cascader.js'></script>
<style scoped>
</style>
