<template>
    <div class="lifecycle"></div>
</template>
<script>
export default {
  name: 'lifecycle',
  data() {
    return {
      isdisabled: true
    }
  }
}
</script>
<style>
.lifecycle {
  /* border:1px solid blue; */
  background: url("./vue-lifecycle.jpg");
  /* background-size:'100% 100%'; */
  background-repeat: "no-repeat";
  background-size: cover;
  background-position: center;
  height: 300px;
  width: 1200px;
}
</style>
