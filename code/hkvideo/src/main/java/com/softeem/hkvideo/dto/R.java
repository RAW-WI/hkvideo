package com.softeem.hkvideo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 该类用于封装API响应。
 * 它包含响应的状态码、消息、数据和成功标识。
 */
@Data
@Accessors(chain = true)  //开启链式编程
public class R {

    /**
     * 响应的状态码，用于表示API调用的结果状态。
     */
    private Integer code;

    /**
     * 响应的消息，用于提供调用结果的详细信息。
     */
    private String msg;

    /**
     * 响应的数据，用于承载API返回的具体内容。
     */
    private Object data;

    /**
     * 成功标识，用于布尔值形式表示API调用是否成功。
     */
    private boolean success;

    /**
     * 创建并返回一个表示操作成功的响应对象。
     * <p>
     * 此方法用于封装一个成功的响应结果，其中包含成功的标识、操作信息和状态码。
     * 它适用于在应用程序中统一处理响应结果的场景，尤其是用于API响应或异步任务处理结果的表示。
     *
     * @return R 返回一个响应对象，表示操作成功。
     */
    public static R ok() {
        R r = new R();
        r.code = 0; // 设置状态码为0，表示操作成功。
        r.msg = "操作成功"; // 设置操作信息为"操作成功"。
        r.success = true; // 设置成功标识为true。
        return r; // 返回封装好的成功响应对象。
    }

    /**
     * 创建一个表示失败的响应对象。
     * <p>
     * 此方法用于快速构建一个响应对象，指示某个操作失败。失败的响应对象包含特定的代码、消息和成功标识，以便调用方可以根据这些信息处理失败情况。
     *
     * @return 返回一个填充了失败信息的响应对象R。
     */
    public static R fail() {
        R r = new R();
        r.code = -1; // 设置状态码为-1，表示操作失败。
        r.msg = "操作失败"; // 设置操作信息为"操作失败"。
        r.success = false; // 设置成功标识为false。
        return r; // 返回封装好的失败响应对象。
    }

}
