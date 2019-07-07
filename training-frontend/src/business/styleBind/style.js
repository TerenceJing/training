export default {
    name: 'styleTest',
    data() {
        return {
            isActive: true,
            hasError: false,
            classObject: {
                active: true,
                'text-danger': true,
                'border': '1px solid red'
            },
            activeColor: 'red',
            fontSize: 16,
            styleObject: {
                color: 'blue',
                fontSize: '13px',
                borderBottom: '1px solid red'
                // 使用驼峰形式代替连接线形式
            },
            styleObject2: {
                borderTop: '1px solid grey'
            }
        }
    },
    methods: {
    },
    computed: {
        classCompute: function () {
            return {

            }
        }
    }
}
