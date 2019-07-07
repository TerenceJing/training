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
    }
  ]
})
