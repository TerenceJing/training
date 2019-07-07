// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
// import Loading from './components/loading.js'
import Toast from './components/toast.js'

Vue.config.productionTip = false
Vue.use(ElementUI)
// Vue.use(Loading)

Vue.use(Toast)
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
