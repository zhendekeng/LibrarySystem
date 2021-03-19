<template>
  <div>
    <head-top></head-top>
    <el-card>
      <el-row :gutter="25">
        <el-col :span="10">
          <el-input v-model="searchInfo"
                    clearable
                    placeholder="查询图书">
            <el-button slot="append"
                       icon="el-icon-search"
                       @click="searchLendLog"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button @click="clearFilter">清除所有过滤器</el-button>
        </el-col>
      </el-row>
      <el-table :data="lendLogList"
                ref="filterTable"
                border
                height="740"
                stripe
                style="width: 100%">
        <el-table-column type="index"></el-table-column>
        <el-table-column prop="bookName"
                         label="书籍名称"
                         width="400">
        </el-table-column>
        <el-table-column prop="lendDate"
                         label="租借日期"
                         sortable
                         width="400">
          <template slot-scope="scope">{{scope.row.lendDate | formatDate}}</template>
        </el-table-column>
        <el-table-column prop="backDate"
                         label="归还日期"
                         sortable
                         width="400">
          <template slot-scope="scope">{{scope.row.backDate | formatDate}}</template>
        </el-table-column>
        <el-table-column prop="state"
                         label="状态"
                         :filters="[{ text: '已还', value: '已还' }, { text: '未还', value: '未还' }]"
                         :filter-method="filterTag"
                         filter-placement="bottom-end">
          <template slot-scope="scope">
            <el-tag :type="scope.row.state=='未还'
                    ? 'primary'
                    : 'success'">
              {{scope.row.state}}
            </el-tag>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>
<script>
import HeadTop from '../HeadTop'
import { formatDate } from '@/assets/js/date'
export default {
  filters: {
    formatDate (time) {
      if (typeof (time) == 'undefined') return undefined
      var date = new Date(time)
      return formatDate(date, 'yyyy-MM-dd hh:mm')
    }
  },
  components: {
    HeadTop
  },
  data () {
    return {
      lendLogList: [],
      searchInfo: '',
      count: 0,
      currentPage: 1
    }
  },
  created () {
    this.getLendLogList()
  },
  methods: {
    async searchLendLog () {
      const { data: res } = await this.$http.get('searchLendLogList', {
        params: {
          info: this.searchInfo,
          userId: window.sessionStorage.getItem('userId')
        }
      })
      if (res.result == 'success') {
        this.lendLogList = res.searchLendLogList
      } else {
        this.$message.error('查询图书失败')
      }
    },
    async getLendLogList () {
      const { data: res } = await this.$http.get('singleLendLogList?userId=' +
        window.sessionStorage.getItem('userId'))
      console.log(res)
      if (res.result == 'success') {
        console.log(1234565)
        this.lendLogList = res.singleLendLogList
      } else {
        this.$message.error('服务器出错')
      }
    },
    filterTag (value, row) {
      return row.state === value
    },
    clearFilter () {
      this.$refs.filterTable.clearFilter()
    }
  }
}
</script>
<style lang="less">
</style>
