<template>
  <div class="open-public-function-btn" @click="clickFunction">
    <slot></slot>
  </div>
</template>
<script>
import openBtn from './OpenBtn'
export default {
  components: { openBtn },
  data() {
    return {}
  },
  methods: {
    clickFunction() {
      this.$emit('click')
    }
  }
}
</script>
<style >
.open-public-function-btn {
  border: 1px solid #5db5ef;
  background: #fff;
  color: #5db5ef;
  font-size: 12px;
  height: 26px;
  line-height: 26px;
  min-width: auto;
  padding: 0 16px;
  display: inline-block;
  border-radius: 4px;
  cursor: pointer;
}
.open-public-function-btn:hover {
  background-color: #5db5ef;
  color: #ffffff;
  border: 1px solid #5db5ef;
}
</style>
