<template>
  <el-scrollbar>
    <!-- :props="props"  -->
    <el-tree v-if="reFresh" :props="props" :load="loadNode" lazy show-checkbox :render-content="renderContent" @check-change="handleCheckChange"
      @node-click="handleNodeClick" :default-expanded-keys="idArr">
    </el-tree>
  </el-scrollbar>
</template>
<script src="./tree.js"></script>
<style lang="less" scoped>
</style>
