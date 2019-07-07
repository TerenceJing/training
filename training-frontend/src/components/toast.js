import ToastComponent from '@/components/toast'

const Toast = {}
// 注册Toast
Toast.install = function (Vue) {
    // 生成一个Vue的子类、同时这个子类也就是组件
    const ToastConstructor = Vue.extend(ToastComponent)
    // 生成一个该子类的实例
    const instance = new ToastConstructor()
    // 创建一个div,将这个vue实例挂载在我创建的div上，并将此div加入全局挂载点内部
    instance.$mount(document.createElement('div'))
    document.body.appendChild(instance.$el)
    // 通过Vue的原型注册一个方法
    // 让所有实例共享这个方法
    Vue.prototype.$toast = (config) => {
        // console.log(config instanceof String)
        debugger
        if (typeof (config) === 'string' || typeof (config) === 'number') {
            instance.message = config
            instance.visible = true
            instance.okClick = () => {
                instance.visible = false
            }
            instance.cancelClick = () => {
                instance.visible = false
            }
            return
        }
        instance.message = config.content
        instance.visible = true
        instance.okClick = config.onOk ? function () {
            instance.visible = false
            config.onOk()
        } : function () {
            instance.visible = false
        }
        instance.cancelClick = config.onCancel ? function () {
            instance.visible = false
            config.onCancel()
        } : function () {
            instance.visible = false
        }
    }
}
export default Toast
