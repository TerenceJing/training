<template>
  <div>
    <el-tree
  :data="data"
  node-key="id"
  :default-expanded-keys="idArr"
  @node-drag-start="handleDragStart"
  @node-drag-enter="handleDragEnter"
  @node-drag-leave="handleDragLeave"
  @node-drag-over="handleDragOver"
  @node-drag-end="handleDragEnd"
  @node-drop="handleDrop"
  draggable
  :allow-drop="allowDrop"
  :allow-drag="allowDrag">
</el-tree>
  </div>
</template>
<script src='./elTree.js'></script>
<style lang="less" scoped>
</style>

