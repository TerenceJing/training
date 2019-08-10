<script src="./hello.js"></script>
<template id="hello">
  <el-scrollbar>
    <el-button type="primary" @click="testOnclick">html与js分页测试</el-button>
    <br/> <br/> <br/><div class="separate-container">Vue基础</div>
    <p>{{ message }}</p>
    <!-- v-bind指令及其缩写： -->
    <span v-bind:title="hoverMessage">鼠标悬停提示</span>
    <span :title="hoverMessage">鼠标悬停提示</span>
    <p v-if="seen">现在你看到我了</p>
    <hr />
    <!-- v-for指令要使用key绑定才能不告警 -->
    <ul>
      <li v-for="todo in todos" :key="todo.text">{{ todo.text }}</li>
    </ul>
    <ul>
      <li v-for="(todo,index) in todos" :key="index">{{index}}.{{todo.text }}</li>
       <li v-for="(todo,index) in todos" :key="index">{{index}}.{{todo.text }}</li>
    </ul>
    <ul>
      <li v-for="(todo,name) in todoObj" :key="name">{{name}}:{{todo }}</li>
    </ul>
    <ul>
      <li v-for="(value,name,index) in todoObj" :key="index">{{index}}.{{name}}:{{value }}</li>
    </ul>
    <!-- v-on指令及其缩写@ -->
    <button v-on:click="reverseMessage">逆转消息</button>
    <button @click="reverseMessage">逆转消息</button>
    <input v-model="message" />

    <br/> <br/> <br/><div class="separate-container">计算属性和侦听属性</div>
    <!-- 计算属性 -->
    <p>Computed reversed message:"{{ reverseMessageByComputation }}"</p>
    <!-- <p>Function reversed message:"{{reverseMessage()}}"</p> -->
    <!-- 侦听属性 -->
    <input v-model="firstName" />
    <p>fullName:{{ fullName }}</p>
  </el-scrollbar>
</template>

<style>
.separate-container{
    border-bottom:1px solid red;
    color:red
}
</style>
