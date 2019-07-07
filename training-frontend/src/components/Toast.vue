<template>
  <div id="toast-container">
      <button @click="okClick">确定</button>
      <button @click="cancelClick">取消</button>
      <div>{{message}}</div>
  </div>
</template>

<script>
export default {
  name: 'toast',
  data() {
    return {
      show1: false
    }
  },
  props: ['message', 'visible'],
  mounted() {},
  methods: {
    okClick: () => {
      this.$emit('onOk')
    },
    cancelClick: () => {
      this.$emit('onCancel')
    }
  },
  watch: {
    visible: {
      handler: function(data) {
        this.show1 = data
      },
      immediate: true
    }
  }
}
</script>

<style scoped>
#toast-container{
    border:1px solid red;
}
</style>
