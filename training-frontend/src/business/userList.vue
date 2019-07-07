<template>
    <div>
        <el-button type="primary" @click="loadingOnClik">进入详情</el-button>
        <!-- <toast>hello</toast> -->
        <openBtn :disabled="isdisabled" @click="updown()">启用</openBtn>
    </div>
</template>
<script>
    export default {
        name: 'userList',
        data() {
            return {
                isdisabled: true
            }
        },
        methods: {
            loadingOnClik() {
                console.log('----')
                this.$toast('系统异常')
                this.$toast({
                    content: '系统异常',
                    onOK: () => {
                        console.log('ok')
                    },
                    onCancel: () => {
                        console.log('cancel')
                    }
                })
            },
            updown() {
                console.log('hello')
            }
        }
    }
</script>
