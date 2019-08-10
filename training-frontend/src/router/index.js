import Vue from 'vue'
import Router from 'vue-router'
import UserList from '@/business/userList'
import Hello from '@/business/separate/Hello.vue'
import Style from '@/business/styleBind/styleTest'
import Component from '@/business/component/component'
import Lifecycle from '@/business/lifecycle/lifecycle'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('@/home/home.vue'),
      // component: resolve => require(['@/home/home.vue'], resolve),
      description: '',
      children: [
        {
          path: '/hello',
          name: 'hello',
          component: Hello,
          description: 'html与js分页测试'
        },
        {
          path: '/style',
          name: 'style',
          component: Style,
          description: '样式绑定测试'
        },
        {
          path: '/lifecycle',
          name: 'lifecycle',
          component: Lifecycle
        },
        {
          path: '/user',
          name: 'userList',
          component: UserList
        },
        {
          path: '/component',
          name: 'component',
          component: Component
        },
        {
          path: '/tree',
          name: 'tree',
          component: () => import('@/business/component/tree.vue')
        },
        {
          path: '/elTree',
          name: 'tree',
          component: () => import('@/business/component/elTree.vue')
        }
      ]
    }
  ]
})
// 异步加载组件，当你访问 / ，才会加载 home.vue
// 加载方式：
// component: resolve => require(['../pages/home.vue'], resolve)
// component: () => import('../pages/home.vue')
// vue-router中，require代替import解决vue项目首页加载时间过久的问题
// vue的路由配置文件(routers.js)，一般使用import引入的写法，当项目打包时路由里的所有component都会打包在一个js中，在项目刚进入首页的时候，就会加载所有的组件，所以导致首页加载较慢，
// 而用require会将component分别打包成不同的js，按需加载，访问此路由时才会加载这个js，所以就避免进入首页时加载内容过多。
// require: 运行时调用，理论上可以运用在代码的任何地方，
// import：编译时调用，必须放在文件开头
