export default {
    name: 'hello',
    data() {
        return {
            message: 'Hello Vue.js!',
            hoverMessage: '现在是' + new Date().toLocaleString(),
            seen: true,
            todos: [
                { text: '煎炒笋衣' },
                { text: '肉末蒸蛋' },
                { text: '肉桂粉皮' }
            ],
            todoObj: {
                title: '今日头条',
                author: 'Tom',
                publishTime: '2019-01-07'
            },
            firstName: 'Tom',
            lastName: 'Smith',
            fullName: ''

        }
    },
    methods: {
        testOnclick() {
            alert('hello')
        },
        reverseMessage: function () {
            this.message = this.message.split('').reverse().join('')
            return this.message
        }
    },
    /** 计算属性适合复杂逻辑计算，并响应式的跟句依赖变化将计算结果提前缓存 */
    computed: {
        // 调用时访问的是计算属性的getter，直接拿值即可。
        reverseMessageByComputation: function () {
            // `this` 指向 vm 实例
            return this.hoverMessage.split('').reverse().join('')
        }
    },
    /** 侦听器适合异步或开销较大的操作 */
    watch: {
        // 侦听firstName
        firstName: function (val) {
            this.fullName = val + ' ' + this.lastName
        },
        // 侦听lastName
        lastName: function (val) {
            this.fullName = this.firstName + ' ' + val
        }
    }
}
