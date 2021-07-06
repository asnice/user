package com.example.user;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.util.ArrayDeque;
import java.util.Queue;

@SpringBootApplication
@EnableRabbit
@EnableDiscoveryClient
@EnableFeignClients
@Component
public class UserApplication {

//	@Value("${foo}")
//	private String foo;

    public static void main(String[] args) throws Exception {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(UserApplication.class, args);
		//UserApplication a = applicationContext.getBean(UserApplication.class);
////		System.out.println(a.foo);
//		System.out.println(UUID.randomUUID().toString());
//		System.out.println(ClassLayout.parseInstance(applicationContext.getBeanFactory()).toPrintable());
//		System.out.println(0x1e50a229   );




        TreeNode<String> root = new TreeNode<>();
        root = TreeNode.put(root, new TreeNode<>(10, "aaaa"));
        root = TreeNode.put(root, new TreeNode<>(18, "ggg"));
        root = TreeNode.put(root, new TreeNode<>(12, "cccc"));
        root = TreeNode.put(root, new TreeNode<>(13, "cccc"));
        TreeNode.print(root);
//        LockTest lockTest = new LockTest();
//        new Thread() {
//            @Override
//            public void run()  {
//                try {
//                    lockTest.a();
//                } catch (Exception e) {
//
//                }
//
//            }
//        }.start();
//
//        new Thread() {
//            @Override
//            public void run()  {
//                try {
//                    lockTest.b();
//                } catch (Exception e) {
//
//                }
//
//            }
//        }.start();

    }

    public static class LockTest  {
        public synchronized void a() throws Exception {
            Thread.sleep(3000);
            System.out.println("aaaa");
        }

        public synchronized void b() throws Exception {
            Thread.sleep(3000);
            System.out.println("bbbb");
        }
    }


//	public static int a (int a, int b) {
//		int res =
//		for (int i = 0; i < 32; i ++) {
//			int m = (a >> i & 1 + b >> i & 1);
//			int m
//		}


//	}

    public static class TreeNode<V> {
        private TreeNode left;
        private TreeNode right;
        private TreeNode parent;
        private Integer key;
        private V value;
        private boolean red;

        public TreeNode() {

        }

        public TreeNode(int key, V value) {
            this.key = key;
            this.value = value;
        }


        public static TreeNode put(TreeNode root, TreeNode x) {
            if (x == null) {
                throw new RuntimeException("x not be null");
            }
            if (root == null) {
                throw new RuntimeException("root not be null");
            }

            root = insertNode(root, x);
            return balanceInsertion(root, x);
        }
        private static TreeNode insertNode(TreeNode root, TreeNode x) {
            if (root.value == null) {
                return x;
            }
            TreeNode t = root;
            while (t != null) {
                if (x.key >= t.key) {
                    if (t.right == null) {
                        t.right = x;
                        x.parent = t;
                        return root;
                    }
                    t = t.right;
                } else {
                    if (t.left == null) {
                        t.left = x;
                        x.parent = t;
                        return root;
                    }
                    t = t.left;
                }
            }
            return root;
        }

        private static TreeNode balanceInsertion(TreeNode root, TreeNode x) {
            x.red = true;
            for (TreeNode xp, xpp, uncle; ;) {
                if ((xp = x.parent) == null) {
                    x.red = false;
                    return x;
                }
                if (!xp.red) {
                    return root;
                }
                xpp = xp.parent;
                if (xp == xpp.left) {
                    uncle = xpp.right;
                    if (uncle != null && uncle.red) {
                        uncle.red = false;
                        xp.red = false;
                        xpp.red = true;
                        x = xpp;
                    } else {
                        if (x == xp.right) {
                            root = left(root, xp);
                            x = xp;
                            xp = x.parent;
                            xpp = xp == null ? null : xp.parent;
                        }
                        if (xp != null) {
                            xp.red = false;
                            if (xpp != null) {
                                xpp.red = true;
                                root = right(root, xpp);
                            }
                        }

                    }
                } else {
                    uncle = xpp.left;
                    if (uncle != null && uncle.red) {
                        uncle.red = false;
                        xp.red = false;
                        xpp.red = true;
                        x = xpp;
                    } else {
                        if (x == xp.left) {
                            root = right(root, xp);
                            x = xp;
                            xp = x.parent;
                            xpp = xp == null ? null : xp.parent;
                        }
                        if (xp != null) {
                            xp.red = false;
                            if (xpp != null) {
                                xpp.red = true;
                                root = left(root, xpp);
                            }
                        }

                    }
                }

            }

        }

        private static TreeNode left (TreeNode root, TreeNode p) {
            TreeNode r, pp, rl;
            if (p != null && (r = p.right) != null) {
                if ((rl = p.right = r.left) != null)
                    rl.parent = p;
                if ((pp = r.parent = p.parent) == null)
                    (root = r).red = false;
                else if (pp.left == p)
                    pp.left = r;
                else
                    pp.right = r;
                r.left = p;
                p.parent = r;
            }
            return root;
        }

        private static TreeNode right (TreeNode root, TreeNode p) {

            TreeNode l, lr, pp;
            if (p != null && (l = p.left) != null) {
                if ((lr = p.left = l.right) != null)
                    lr.parent = p;
                if ((pp = l.parent = p.parent) == null)
                    (root = l).red = false;
                else if (pp.right == p)
                    pp.right = l;
                else
                    pp.left = l;
                l.right = p;
                p.parent = l;
            }
            return root;
        }

        private static void print(TreeNode root) {
            if (root == null) {
                return;
            }
            Queue<TreeNode> queue = new ArrayDeque<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                TreeNode treeNode = queue.poll();
                if (treeNode.left != null) {
                    queue.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.add(treeNode.right);
                }
                System.out.println(treeNode.key + " : " +treeNode.value);
            }
        }
    }



}
